package LojaEletronicos;

public class Tablet extends Equipamentos {
	
	private static final long serialVersionUID = 1L;
	private int quantidadeChips;
	
	
	public Tablet (String nome, String marca, String modelo, int quantidadeChips) {
		super(nome, marca, modelo);
		this.setQuantidadeChips(quantidadeChips);
		this.tamanhoTela = "tela media";
	}
	
	public int getQuantidadeChips() {
		return quantidadeChips;
	}

	public void setQuantidadeChips(int quantidadeChips) {
		this.quantidadeChips = quantidadeChips;
	}
	
	public String toString() {
		return super.toString() + "Quantidade de chips disponivel:"+ getQuantidadeChips() +"\n";
	}

	
}