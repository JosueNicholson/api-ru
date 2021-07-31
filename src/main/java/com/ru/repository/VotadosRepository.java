package com.ru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ru.model.Votados;

@Repository
public interface VotadosRepository extends JpaRepository<Votados, String>{

	Votados findByHashMatricula(String s);
	
	Votados findByHashMatriculaOrderByHora(String imei);
}
