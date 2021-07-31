package com.ru.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Arduino {

	@Id
	private int id = 1;
	
	private Calendar ultimaAtualizacao;
	@OneToOne
	private Refeicao ultimaRefeicaoInserida;

	
	
	public Arduino() {
	}

	public Arduino(Calendar ultimaAtualizacao, Refeicao refeicao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
		this.ultimaRefeicaoInserida = refeicao;
	}

	public Refeicao getUltimaRefeicaoInserida() {
		return ultimaRefeicaoInserida;
	}

	public void setUltimaRefeicaoInserida(Refeicao ultimaRefeicaoInserida) {
		this.ultimaRefeicaoInserida = ultimaRefeicaoInserida;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getUltimaAtualizacao() {
		return ultimaAtualizacao;
	}

	public void setUltimaAtualizacao(Calendar ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}
	
}
