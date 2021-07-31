package com.ru.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ru.model.Arduino;
import com.ru.model.Refeicao;
import com.ru.repository.ArduinoRepository;

@Service
public class ArduinoService {
	
	@Autowired
	private ArduinoRepository repository;
	
	public void saveOrUpdate(Arduino arduino) {
		repository.save(arduino);
	}
	
	public void updateUltimaAtualizacao(Refeicao refeicao) {
		repository.save(new Arduino(Calendar.getInstance(),refeicao));
	}
	
}
