package LojaEletronicos;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;





public class LojaEletronicos {
	
	private ArrayList<Equipamentos> Equipamentos;
	
	public LojaEletronicos() {		
		this.Equipamentos = new ArrayList<Equipamentos>();
	}
	
	public void addEquipamentos(Equipamentos equip) {
		this.Equipamentos.add(equip);		
	}
	
	public void listarEquipamentos() {
		for (Equipamentos equip:Equipamentos) {
			System.out.println(equip.toString());
		}
	}
	
	public void excluirEquipamentos(Equipamentos equip) {
		if(this.Equipamentos.contains(equip)) {
			this.Equipamentos.remove(equip);
			System.out.println("[Equipamentos " + equip.toString() + "Realizado a exclusão]\n");
		}
		else {
			System.out.println("[Não foi encontrado]\n");
		}
	}
	
	public void excluirTodosEquipamentos() {
		Equipamentos.clear();
		System.out.println("[Todos Equipamentos excluidos com sucesso!]\n");
	}
	 
	public void salvarEquipamentos() {
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream (new FileOutputStream("c:\\temp\\petStore.dados"));
			for(Equipamentos equip:Equipamentos) {
				outputStream.writeObject(equip);
			}
		}catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if (outputStream != null ) {
					outputStream.flush();
					outputStream.close();
				}
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	public void recuperarEquipamentos() {
		ObjectInputStream inputStream = null;
		try {
			inputStream	= new ObjectInputStream (new FileInputStream ("c:\\temp\\petStore.dados"));
			Object obj = null;
			while((obj = inputStream.readObject ()) != null) {
				if (obj instanceof Tablet)  
					this.Equipamentos.add((Tablet)obj);
				else if (obj instanceof Smartwatch)  
					this.Equipamentos.add((Smartwatch)obj);
				else if (obj instanceof Notebook)  
					this.Equipamentos.add((Notebook)obj);
				
			}
		}catch (EOFException ex) {     // when EOF is reached
			System.out.println ("End of file reached");
		}catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if (inputStream != null ) {
					inputStream.close();
					System.out.println("Equipamentoss recuperados com sucesso!\n");
				}
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		LojaEletronicos loja1  = new LojaEletronicos();

		Tablet tablet1    	= new Tablet("tablet1","Motorola", "G20", 2);
		Tablet tablet2 	= new Tablet("tablet2","apple", "ipad", 1);
		Smartwatch  relogio1    = new Smartwatch ("relogio1","apple", "apple watch", "borracha");
		Smartwatch  relogio2    = new Smartwatch ("relogio2","china", "wp32a5", "couro");
		Notebook  note1     	= new Notebook ("note1", "Dell", "Dell gamer","i7");
		Notebook  note2     	= new Notebook ("note2", "positivo", "positivo note2","celeron");
		
		
		loja1.addEquipamentos(tablet1);
		loja1.addEquipamentos(tablet2);
		loja1.addEquipamentos(relogio1);
		loja1.addEquipamentos(relogio2);
		loja1.addEquipamentos(note1);
		loja1.addEquipamentos(note2);
		loja1.listarEquipamentos();
		loja1.salvarEquipamentos();
		loja1.excluirEquipamentos(note2);
		loja1.listarEquipamentos();
		loja1.excluirTodosEquipamentos();
		loja1.listarEquipamentos();
		loja1.recuperarEquipamentos();
		loja1.listarEquipamentos();
	}


}
