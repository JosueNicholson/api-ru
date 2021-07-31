package com.ru.model;

public enum Qualidade {
	BOM(0), REGULAR(1), RUIM(2);
	
	private int posicao;

	private Qualidade(int posicao) {
		this.posicao = posicao;
	}

	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}
	
	
}
