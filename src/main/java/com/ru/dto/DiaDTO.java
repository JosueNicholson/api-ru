package com.ru.dto;

import com.ru.model.Dia;
import com.ru.model.Refeicao;

public class DiaDTO {
	private int id;
	
	private RefeicaoDTO almoco;

	private RefeicaoDTO janta;
	
	private int dia;
	
	private int mes;
	
	private int ano;
	
	public DiaDTO(Dia dia) {
		this.dia = dia.getDia();
		this.mes = dia.getMes();
		this.ano = dia.getAno();
		this.id = dia.getId();
	}


	public RefeicaoDTO getAlmoco() {
		return almoco;
	}


	public void setAlmoco(RefeicaoDTO almoco) {
		this.almoco = almoco;
	}


	public RefeicaoDTO getJanta() {
		return janta;
	}


	public void setJanta(RefeicaoDTO janta) {
		this.janta = janta;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getDia() {
		return dia;
	}


	public void setDia(int dia) {
		this.dia = dia;
	}


	public int getMes() {
		return mes;
	}


	public void setMes(int mes) {
		this.mes = mes;
	}


	public int getAno() {
		return ano;
	}


	public void setAno(int ano) {
		this.ano = ano;
	}

	
	
}
