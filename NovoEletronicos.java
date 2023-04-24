package LojaEletronicos;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;



public class NovoEletronicos {
	private ArrayList<Equipamentos> Equipamentos;
	
	public NovoEletronicos() {		
		this.Equipamentos = new ArrayList<Equipamentos>();
	}
	
	public String[] verificaValores (String [] dadosIn){
		String [] dadosOut = new String [dadosIn.length];

		for (int i = 0; i < dadosIn.length; i++)
			dadosOut[i] = JOptionPane.showInputDialog  ("Entre com " + dadosIn[i]+ ": ");

		return dadosOut;
	}
	
	public Tablet lephone() {

		String [] valores = new String [4];
		String [] nomeVal = {"Nome", "marca", "modelo", "quantidadeChips"};
		valores = verificaValores (nomeVal);

		int quantidadeChips = this.retornaInteiro(valores[3]);

		Tablet Tablet = new Tablet (valores[0], valores[1], valores[2], quantidadeChips);
		return Tablet;		
	}
	
	public Smartwatch lewatch (){

		String [] valores = new String [4];
		String [] nomeVal = {"Nome", "marca", "modelo", "tipoPulseira"};
		valores = verificaValores (nomeVal);

		Smartwatch Smartwatch = new Smartwatch (valores[0], valores[1], valores[2], valores[3]);
		return Smartwatch;		
	}
	
	public Notebook lenote (){

		String [] valores = new String [4];
		String [] nomeVal = {"Nome", "marca", "modelo", "processador"};
		valores = verificaValores (nomeVal);

		Notebook Notebook = new Notebook (valores[0], valores[1], valores[2], valores[3]);
		return Notebook;		
	}
	
	private boolean intValido(String s) {
		try {
			Integer.parseInt(s); // Metodo est�tico, que tenta tranformar uma string em inteiro
			return true;
		} catch (NumberFormatException e) { // Nao conseguiu tranformar em inteiro e gera erro
			return false;
		}
	}
	
	public int retornaInteiro(String entrada) { // retorna um valor inteiro
		int numInt;

		//Enquanto nao for possivel converter o valor de entrada para inteiro, permanece no loop
		while (!this.intValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um numero inteiro.");
		}
		return Integer.parseInt(entrada);
	}
	
	public void salvaEquipamentos (ArrayList<Equipamentos> Equipamentos){
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream 
					(new FileOutputStream("c:\\temp\\petStore.dados"));
			for (int i=0; i < Equipamentos.size(); i++)
				outputStream.writeObject(Equipamentos.get(i));
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Impossível criar arquivo!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  //Close the ObjectOutputStream
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("finally")
	public ArrayList<Equipamentos> recuperaEquipamentos (){
		ArrayList<Equipamentos> EquipamentossTemp = new ArrayList<Equipamentos>();

		ObjectInputStream inputStream = null;

		try {	
			inputStream = new ObjectInputStream
					(new FileInputStream("c:\\temp\\petStore.dados"));
			Object obj = null;
			while ((obj = inputStream.readObject()) != null) {
				if (obj instanceof Equipamentos) {
					EquipamentossTemp.add((Equipamentos) obj);
				}   
			}          
		} catch (EOFException ex) { // when EOF is reached
			System.out.println("Fim de arquivo.");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Arquivo com Equipamentoss nao existe!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  //Close the ObjectInputStream
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
			return EquipamentossTemp;
		}
	}
	public void menuNovoEletronicos (){

		String menu = "";
		String entrada;
		int    opc1, opc2;

		do {
			menu = "Controle Loja de Eletronicos\n" +
					"Opções:\n" + 
					"1. Entrar Equipamentoss\n" +
					"2. Exibir Equipamentoss\n" +
					"3. Limpar Equipamentoss\n" +
					"4. Gravar Equipamentoss\n" +
					"5. Recuperar Equipamentoss\n" +
					"9. Sair";
			entrada = JOptionPane.showInputDialog (menu + "\n\n");
			opc1 = this.retornaInteiro(entrada);

			switch (opc1) {
			case 1:// Entrar dados
				menu = "Entrada de Equipamentoss\n" +
						"Opções:\n" + 
						"1. Tablet\n" +
						"2. Smartwatch\n"+
						"3. Notebook\n";

				entrada = JOptionPane.showInputDialog (menu + "\n\n");
				opc2 = this.retornaInteiro(entrada);

				switch (opc2){
				case 1: Equipamentos.add((Equipamentos)lephone());
				break;
				case 2: Equipamentos.add((Equipamentos)lewatch());
				break;
				case 3: Equipamentos.add((Equipamentos)lenote());
				break;
				default: 
					JOptionPane.showMessageDialog(null,"Equipamentos para entrada não escolhido!");
				}

				break;
			case 2: // Exibir dados
				if (Equipamentos.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com Equipamentoss primeiramente");
					break;
				}
				String dados = "";
				for (int i=0; i < Equipamentos.size(); i++)	{
					dados += Equipamentos.get(i).toString() + "---------------\n";
				}
				JOptionPane.showMessageDialog(null,dados);
				break;
			case 3: // Limpar Dados
				if (Equipamentos.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com Equipamentoss primeiramente");
					break;
				}
				Equipamentos.clear();
				JOptionPane.showMessageDialog(null,"Dados LIMPOS com sucesso!");
				break;
			case 4: // Grava Dados
				if (Equipamentos.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com Equipamentoss primeiramente");
					break;
				}
				salvaEquipamentos(Equipamentos);
				JOptionPane.showMessageDialog(null,"Dados SALVOS com sucesso!");
				break;
			case 5: // Recupera Dados
				Equipamentos = recuperaEquipamentos();
				if (Equipamentos.size() == 0) {
					JOptionPane.showMessageDialog(null,"Sem dados para apresentar.");
					break;
				}
				JOptionPane.showMessageDialog(null,"Dados RECUPERADOS com sucesso!");
				break;
			case 9:
				JOptionPane.showMessageDialog(null,"Fim do aplicativo Loja de Equipamentoss");
				break;
			}
		} while (opc1 != 9);
	}
	
	public static void main (String [] args){

		NovoEletronicos loja1 = new NovoEletronicos ();
		loja1.menuNovoEletronicos();
		
	}

}
