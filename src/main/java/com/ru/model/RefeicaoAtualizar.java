package com.ru.model;


public class RefeicaoAtualizar {
	private String proteina;
	private Qualidade qualidade;
	private String matricula;
	private String comentario;
	
	public RefeicaoAtualizar(String proteina, Qualidade qualidade, String matricula, String comentario) {
		this.proteina = proteina;
		this.qualidade = qualidade;
		this.matricula = matricula;
		this.comentario = comentario;
	}
	
	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


	public String getProteina() {
		return proteina;
	}
	public void setProteina(String proteina) {
		this.proteina = proteina;
	}
	public Qualidade getQualidade() {
		return qualidade;
	}
	public void setQualidade(Qualidade qualidade) {
		this.qualidade = qualidade;
	}
	
	
}
