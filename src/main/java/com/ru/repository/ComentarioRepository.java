package com.ru.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ru.dto.ComentarioDTO;
import com.ru.model.Comentario;
import com.ru.model.Refeicao;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer>{
	Comentario findById(int id);
	
	List<ComentarioDTO> findByRefeicao(Refeicao refeicao);
}
