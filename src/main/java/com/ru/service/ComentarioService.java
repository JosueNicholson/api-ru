package com.ru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ru.dto.ComentarioDTO;
import com.ru.model.Comentario;
import com.ru.model.Refeicao;
import com.ru.repository.ComentarioRepository;


@Service
public class ComentarioService {
	
	@Autowired
	private ComentarioRepository comentarioRepository;

	
	public List<Comentario> findAll() {
		return comentarioRepository.findAll();
	}
	
	public Comentario findById(Comentario comentario) {
		return comentarioRepository.findById(comentario.getId());
	}

	public Comentario save(Comentario comentario) {
		return comentarioRepository.save(comentario);
	}
	
	public List<ComentarioDTO> findByRefeicao(Refeicao refeicao) {
		return comentarioRepository.findByRefeicao(refeicao);
	}
}
