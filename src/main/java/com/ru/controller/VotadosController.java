package com.ru.controller;

import java.util.Calendar;
import java.util.TimeZone;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ru.model.CustomException;
import com.ru.model.Dia;
import com.ru.model.Refeicao;
import com.ru.service.DiaService;
import com.ru.service.RefeicaoService;
import com.ru.service.VotadosService;
import com.ru.utils.Hasher;

@RestController
@RequestMapping("votados")
public class VotadosController {
	
	
	@Autowired
	private DiaService diaService;
	
	@Autowired
	private RefeicaoService refeicaoService;
	
	@Autowired
	private DiaController diaController;
	
	@Autowired
	VotadosService votadosService;

	@GetMapping("podeVotar/{matricula}")
	public ResponseEntity<Object> podeVotar(@PathVariable String matricula) {
		Object body = diaController.getUltimoDia().getBody();
		Dia ultimoDia = body != null ? (Dia) body : null;
		Calendar hoje = Calendar.getInstance(TimeZone.getTimeZone("GMT-03:00"));
		if(ultimoDia == null || hoje.get(Calendar.DAY_OF_MONTH) != ultimoDia.getDia()) {
			Dia novoDia = new Dia(hoje.get(Calendar.DAY_OF_MONTH), hoje.get(Calendar.MONTH)+1, hoje.get(Calendar.YEAR));
			if(!novoDia.isAlmocoTime() && !novoDia.isJantaTime()) {
				return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Não está em horário de refeição");
			}
			salvarNovoDia(novoDia);
			return ResponseEntity.ok("");
		}
		else if(!ultimoDia.isAlmocoTime() && !ultimoDia.isJantaTime()) 
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Não está em horário de refeição");
		String matriculaHash = "";
		try {
			matriculaHash = Hasher.getHash(matricula);
		} catch (NoSuchAlgorithmException e) {
			ResponseEntity.status(HttpStatus.BAD_REQUEST).body("algo deu errado com a matricula");
		}
		if(!votadosService.jaVotou(matriculaHash, ultimoDia)) {
			return ResponseEntity.ok("");
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body("Voce nao pode votar");
		
	}
	
	private void salvarNovoDia(Dia novoDia) {
		int[] arrayVazio = {0,0,0};
		novoDia.setAlmoco(refeicaoService.save(new Refeicao(arrayVazio, arrayVazio, arrayVazio)));
		novoDia.setJanta(refeicaoService.save(new Refeicao(arrayVazio, arrayVazio, arrayVazio)));
		diaService.save(novoDia);
		System.out.println("Novo dia criado! dia: "+ novoDia.getDia() + " mes: " + novoDia.getMes() + " ano: " + novoDia.getAno());
	}
}
