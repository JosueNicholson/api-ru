package com.ru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ru.model.Refeicao;
import com.ru.repository.RefeicaoRepository;

@Service
public class RefeicaoService {

	@Autowired
	private RefeicaoRepository repository;
		
	public Refeicao save(Refeicao refeicao) {
		return repository.save(refeicao);
	}
	
	public List<Refeicao> findAll(){
		return repository.findAll();
	}
	
}
