package com.ru.dto;

import com.ru.model.Refeicao;

public class ComentarioDTO {
	private int id;
	
	String comentario;

	public ComentarioDTO(String comentario) {
		this.comentario = comentario;
	}
	
	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getId() {
		return this.id;
	}
	
}
