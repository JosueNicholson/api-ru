package com.ru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ru.model.Refeicao;

@Repository
public interface RefeicaoRepository extends JpaRepository<Refeicao, Integer> {

}
