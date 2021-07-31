package com.ru.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.hibernate.validator.constraints.Length;

@Entity
public class Votados {
	
	@Id
	@Length(min = 32, max = 32)
	private String hashMatricula;
	private Calendar hora;
	

	public Votados(String hashMatricula) {
		super();
		this.hashMatricula = hashMatricula;
		this.hora = Calendar.getInstance();
	}
	
	public Votados() {}

	public String getHashMatricula() {
		return hashMatricula;
	}

	public void setHashMatricula(String hashMatricula) {
		this.hashMatricula = hashMatricula;
	}
	
	public Calendar getHora() {
		return hora;
	}

}
