package com.ru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ru.model.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer>{
	Comentario findById(int id);
}
