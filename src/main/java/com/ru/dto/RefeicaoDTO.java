package com.ru.dto;

import java.util.ArrayList;
import java.util.List;

import com.ru.model.Comentario;
import com.ru.model.Refeicao;

public class RefeicaoDTO {
	private Long id;
	private int[] carneVermelha;
	private int[] carneBranca;
	private int[] vegetariano;
	private List<ComentarioDTO> comentarios;
	
	public RefeicaoDTO(Refeicao refeicao) {
		this.id = refeicao.getId();
		this.carneBranca = refeicao.getCarneBranca();
		this.carneVermelha = refeicao.getCarneVermelha();
		this.vegetariano = refeicao.getVegetariano();
		this.comentarios = new ArrayList<ComentarioDTO>();
	}

	public List<ComentarioDTO> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<ComentarioDTO> comentarios) {
		this.comentarios = comentarios;
	}
	
	public Long getId() {
		return id;
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

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
