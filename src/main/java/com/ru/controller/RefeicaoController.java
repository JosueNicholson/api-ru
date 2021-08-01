package com.ru.controller;

import java.util.List;

import javax.xml.bind.DatatypeConverter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ru.model.Comentario;
import com.ru.model.CustomException;
import com.ru.model.Dia;
import com.ru.model.Refeicao;
import com.ru.model.RefeicaoAtualizar;
import com.ru.service.ComentarioService;
import com.ru.service.RefeicaoService;
import com.ru.service.VotadosService;
import com.ru.utils.Hasher;

@RestController
@RequestMapping("refeicao")
public class RefeicaoController {

	@Autowired
	private RefeicaoService service;
	
	@Autowired
	private ComentarioService comentarioService;
	
	@Autowired
	private VotadosService votadosService;
	
	@Autowired
	private DiaController diaController;
	
	

	@GetMapping("/ultimaRefeicaoID")
	public ResponseEntity<String> ultimaRefeicaoID() {
		long id = -1;
		List<Refeicao> aux = service.findAll();
		id = aux.get(aux.size() - 1).getId();

		return ResponseEntity.ok("Id:" + id);
	}

	@PutMapping("/atualizar")
	public ResponseEntity<Object> atualizarRefeicao(@RequestBody RefeicaoAtualizar refeicao) {
		String matricula = refeicao.getMatricula();
		String matriculaHash = "";
		try {
			matriculaHash = Hasher.getHash(matricula);
		} catch (NoSuchAlgorithmException e) {
			ResponseEntity.status(HttpStatus.BAD_REQUEST).body("algo deu errado com a matricula");
		}
		if(matriculaHash.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("matricula é obrigatório");
		
		Object body = diaController.getUltimoDia().getBody();
		Dia hoje = body != null ? (Dia) body : null ;
		try {
			votadosService.addVotados(matriculaHash);			
		} catch (CustomException e) {
			return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
		}

		Refeicao atual = hoje.isAlmocoTime() ? hoje.getAlmoco() : hoje.getJanta();
		if(refeicao.getComentario() != null) {
			Comentario comentario = new Comentario(refeicao.getComentario());
			comentario.setRefeicao(atual);
			comentarioService.save(comentario);
		}
		atual.atualizarRefeicao(refeicao);
		return ResponseEntity.ok(service.save(atual));	
	}

}
