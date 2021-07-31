package com.ru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ru.model.Dia;
import com.ru.repository.DiaRepository;
import com.ru.repository.RefeicaoRepository;

@Service
public class DiaService {
	
	@Autowired
	private DiaRepository repository;

	@Autowired
	RefeicaoRepository r;
	
	public List<Dia> findAll() {
		return repository.findAll();
	}
	
	public Dia findById(Dia dia) {
		return repository.findById(dia.getId());
	}

	public Dia save(Dia dia) {
		return repository.save(dia);
	}

	public Dia findByDiaAndMesAndAno(int dia, int mes, int ano) {
		return repository.findByDiaAndMesAndAno(dia,mes,ano);
	}

}
