package com.ru.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Refeicao {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	private int[] carneVermelha;
	private int[] carneBranca;
	private int[] vegetariano;
	
	public Refeicao() {}
	
	public Refeicao(int[] carneVermelha, int[] carneBranca, int[] vegetariano) {
		this.carneVermelha = carneVermelha;
		this.carneBranca = carneBranca;
		this.vegetariano = vegetariano;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int[] getCarneVermelha() {
		return carneVermelha;
	}
	public void setCarneVermelha(int[] carneVermelha) {
		this.carneVermelha = carneVermelha;
	}
	public int[] getCarneBranca() {
		return carneBranca;
	}
	public void setCarneBranca(int[] carneBranca) {
		this.carneBranca = carneBranca;
	}
	public int[] getVegetariano() {
		return vegetariano;
	}
	public void setVegetariano(int[] vegetariano) {
		this.vegetariano = vegetariano;
	}

	public Refeicao atualizarRefeicao(RefeicaoAtualizar refeicao) {
		switch (refeicao.getProteina()) {
		case "carneVermelha":
			carneVermelha[refeicao.getQualidade().getPosicao()]++;
			break;
		case "carneBranca":
			carneBranca[refeicao.getQualidade().getPosicao()]++;
			break;
		case "vegetariano":
			vegetariano[refeicao.getQualidade().getPosicao()]++;
			break;

		}
		return this;
	}
	
	public String toString() {
		String refeicao = "carne Vermelha: ";
		for(int i=0; i<3; i++) {
			refeicao += this.carneVermelha[i];
		}
		refeicao += "\n carne branca: ";
		for(int i=0; i<3; i++) {
			refeicao += this.carneBranca[i];
		}
		refeicao +="\n vegetariano: ";
		for(int i=0; i<3; i++) {
			refeicao += this.vegetariano[i];
		}
		return refeicao;
	}
	
}
