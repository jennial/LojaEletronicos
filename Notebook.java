package LojaEletronicos;

public class Notebook extends Equipamentos{
	private static final long serialVersionUID = 1L;
	private String processador;
	
	
	public Notebook (String nome, String marca, String modelo, String processador) {
		super(nome, marca, modelo);
		this.setProcessador(processador);
		this.tamanhoTela = "tela grande";
	}


	public String getProcessador() {
		return processador;
	}


	public void setProcessador(String processador) {
		this.processador = processador;
	}
	
	public String toString() {
		return super.toString() + "Tipo de processador:"+ getProcessador() +"\n";		
	}
	
}