package com.ru.model;

import java.util.Calendar;
import java.util.TimeZone;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Dia {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	
	@OneToOne
	private Refeicao almoco;
	@OneToOne
	private Refeicao janta;
	
	private int dia;
	
	private int mes;
	
	private int ano;	
	
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

	
	
	public Dia(int dia, int mes, int ano) {
		super();
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
	}

	public Dia() {}
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}

	public Refeicao getAlmoco() {
		return almoco;
	}

	public void setAlmoco(Refeicao almoco) {
		this.almoco = almoco;
	}

	public Refeicao getJanta() {
		return janta;
	}

	public void setJanta(Refeicao janta) {
		this.janta = janta;
	}
	
	public boolean isAlmocoTime() {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT-03:00"));
		
		Calendar inicioAlmoco = Calendar.getInstance(TimeZone.getTimeZone("GMT-03:00"));
		inicioAlmoco.set(ano, mes-1, dia, 02, 00);
		
		Calendar fimAlmoco = Calendar.getInstance(TimeZone.getTimeZone("GMT-03:00"));
		fimAlmoco.set(ano, mes-1, dia, 11, 10);
		
		if(c.after(inicioAlmoco) && c.before(fimAlmoco))
			return true;
		
		return false;
	}
	
	public boolean isJantaTime() {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT-03:00"));
		Calendar inicioJanta = Calendar.getInstance(TimeZone.getTimeZone("GMT-03:00"));
		inicioJanta.set(ano, mes-1, dia, 11, 15);
		
		Calendar fimJanta= Calendar.getInstance(TimeZone.getTimeZone("GMT-03:00"));
		fimJanta.set(ano, mes-1, dia, 23, 00);
		
		if(c.after(inicioJanta) && c.before(fimJanta))
			return true;
		
		return false;
	}
	
	public Refeicao getUltimaRefeicao() {
		return almoco != null ? almoco : janta;
	}
	
	public String toString() {
		return "almoco: " + getAlmoco().toString() + "\njanta: " + getJanta().toString();
		
	}
	
}
