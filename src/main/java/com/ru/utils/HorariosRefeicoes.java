package com.ru.utils;

import java.util.Calendar;

public class HorariosRefeicoes {
	int dia, mes, ano;
	
	public HorariosRefeicoes() {
		Calendar dataHoje = Calendar.getInstance();
		dia = dataHoje.get(Calendar.DAY_OF_MONTH);
		mes = dataHoje.get(Calendar.MONTH);
		ano = dataHoje.get(Calendar.YEAR);
	}

	public Calendar getInicioAlmoco() {
		Calendar inicioAlmoco = Calendar.getInstance();
		inicioAlmoco.set(ano, mes, dia, 02, 00);
		return inicioAlmoco;
	}
	
	public Calendar getFimAlmoco() {
		Calendar fimAlmoco = Calendar.getInstance();
		fimAlmoco.set(ano, mes, dia, 11, 10);
		return fimAlmoco;
	}
	
	public Calendar getInicioJanta() {
		Calendar inicioJanta = Calendar.getInstance();
		inicioJanta.set(ano, mes, dia, 11, 15);
		return inicioJanta;
	}
	
	public Calendar getFimJanta() {
		Calendar fimJanta = Calendar.getInstance();
		fimJanta.set(ano, mes, dia, 23, 00);
		return fimJanta;
	}
}
