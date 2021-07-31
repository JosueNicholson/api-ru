package com.ru.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ru.controller.DiaController;
import com.ru.model.CustomException;
import com.ru.model.Dia;
import com.ru.model.Votados;
import com.ru.repository.VotadosRepository;
import com.ru.utils.HorariosRefeicoes;

@Service
public class VotadosService {

	@Autowired
	private VotadosRepository repository;

	@Autowired
	private DiaController diaController;

	public void limparVotados() {
		repository.deleteAll();
	}

	public Votados addVotados(String matriculaHash) {
		Object body = diaController.getUltimoDia().getBody();
		Dia hoje = body != null ? (Dia) body : null;
		if (!hoje.isAlmocoTime() && !hoje.isJantaTime()) {
			throw new CustomException("Não está em horário de refeição", HttpStatus.PRECONDITION_FAILED);
		}
		Votados votante = new Votados(matriculaHash);
		if (jaVotou(matriculaHash, hoje))
			throw new CustomException("Você já votou nesta refeição.", HttpStatus.CONFLICT);
		return repository.save(votante);
	}

	public boolean jaVotou(String matriculaHash, Dia hoje) {
		Votados votados = repository.findByHashMatriculaOrderByHora(matriculaHash);
		if (votados == null)
			return false;
		HorariosRefeicoes horarios = new HorariosRefeicoes();
		boolean jaVotouNoAlmoco = this.jaVotouNaRefeicao(votados.getHora(), horarios.getInicioAlmoco(),
				horarios.getFimAlmoco());
		boolean jaVotouNaJanta = this.jaVotouNaRefeicao(votados.getHora(), horarios.getInicioJanta(),
				horarios.getFimJanta());
		if ((hoje.isAlmocoTime() && jaVotouNoAlmoco) || (hoje.isJantaTime() && jaVotouNaJanta))
			return true;
		return false;
	}

	private boolean jaVotouNaRefeicao(Calendar horaDoVoto, Calendar inicioRefeicao, Calendar fimRefeicao) {
		if (horaDoVoto.before(fimRefeicao) && horaDoVoto.after(inicioRefeicao))
			return true;
		return false;
	}

}
