package com.ru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ru.model.Dia;

@Repository
public interface DiaRepository extends JpaRepository<Dia, Integer>{
	Dia findById(int id);

	Dia findByDiaAndMesAndAno(int dia, int mes, int ano);
}
