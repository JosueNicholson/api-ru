package com.ru.controller;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ru.dto.ComentarioDTO;
import com.ru.dto.DiaDTO;
import com.ru.dto.RefeicaoDTO;
import com.ru.model.Comentario;
import com.ru.model.Dia;
import com.ru.model.Refeicao;
import com.ru.service.ArduinoService;
import com.ru.service.ComentarioService;
import com.ru.service.DiaService;
import com.ru.service.RefeicaoService;

@RestController
@RequestMapping("dia")
public class DiaController {
	
	@Autowired
	private DiaService service;
	
	@Autowired
	private RefeicaoService rservice;
	
	@Autowired
	private ArduinoService aservice;
	
	@Autowired
	private ComentarioService cservice;
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<Collection<Dia>> getAll(){
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{ano}/{mes}/{dia}")
	public ResponseEntity<DiaDTO> getDia(@PathVariable int dia, @PathVariable int mes, @PathVariable int ano){
		Dia aux = service.findByDiaAndMesAndAno(dia, mes, ano);
		DiaDTO response = new DiaDTO(aux);
		RefeicaoDTO almoco = new RefeicaoDTO(aux.getAlmoco());
		RefeicaoDTO janta = new RefeicaoDTO(aux.getJanta());
		List<ComentarioDTO> comentariosAlmoco = cservice.findByRefeicao(aux.getAlmoco());
		List<ComentarioDTO> comentariosJanta = cservice.findByRefeicao(aux.getJanta());
		almoco.setComentarios(comentariosAlmoco);
		janta.setComentarios(comentariosJanta);
		response.setAlmoco(almoco);
		response.setJanta(janta);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("")
	public ResponseEntity<Dia> postDia(@RequestBody Dia d){
		if(d.getAlmoco() != null)
			d.setAlmoco(rservice.save(d.getAlmoco()));
		if(d.getJanta() != null)
			d.setJanta(rservice.save(d.getJanta()));
		return ResponseEntity.ok(service.save(d));
	}
	
	@PostMapping("/dias/{quantidade}")
	public ResponseEntity<String> postDias(@RequestBody Dia[] dias, @PathVariable int quantidade){
		int i = 0;
		while(i < quantidade) {
			Dia aux = service.findByDiaAndMesAndAno(dias[i].getDia(),dias[i].getMes(),dias[i].getAno());
			if(aux == null) {
				dias[i].setAlmoco(rservice.save(dias[i].getAlmoco()));
				dias[i].setJanta(rservice.save(dias[i].getJanta()));
				service.save(dias[i]);
			}
			else {
				dias[i].setId(aux.getId());
				
				Refeicao raux = aux.getAlmoco();
				dias[i].getAlmoco().setId(raux.getId());
				rservice.save(dias[i].getAlmoco());
				raux = aux.getJanta();
				dias[i].getJanta().setId(raux.getId());
				rservice.save(dias[i].getJanta());
				
				service.save(dias[i]);
				aservice.updateUltimaAtualizacao(dias[i].getJanta());
			}
			i++;
		}
		return ResponseEntity.ok("Atualizado");
	}
	
	@GetMapping("/ultimoDia")
	public ResponseEntity<Object> getUltimoDia(){
		
		List<Dia> dias  = service.findAll();
		if(dias.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		Dia aux = dias.get(dias.size()-1);
		return ResponseEntity.ok(aux);
	}
	
	@GetMapping("/getDataEHora")
	public ResponseEntity<String> getDataEHora(){
		String resposta = "Dia";
		LocalDateTime inst = LocalDateTime.now(); 
		
		resposta += filtro(inst.getHour()) + "";
		resposta += filtro(inst.getMinute()) + "";
		resposta += filtro(inst.getSecond());
		resposta += filtro(inst.getDayOfMonth()) + "";
		resposta += filtro(inst.getMonthValue()) + "";
		resposta += filtro(inst.getYear());
		return ResponseEntity.ok(resposta);
	}
	
	private String filtro(int valor) {
		return valor < 10 ? "0"+valor: ""+valor;
	}
	
	@PostMapping("/refeicao")
	public ResponseEntity<Dia> adicionarRefeicao(@RequestBody Refeicao refeicao){
		
		Calendar c = Calendar.getInstance();
		Dia dia = service.findByDiaAndMesAndAno(
				c.get(Calendar.DAY_OF_MONTH),
				c.get(Calendar.MONTH),
				c.get(Calendar.YEAR));
		
		if(dia == null)
			dia = service.save(new Dia(
					c.get(Calendar.DAY_OF_MONTH),
					c.get(Calendar.MONTH),
					c.get(Calendar.YEAR)));
		
		if(dia.isAlmocoTime()) {
			if(dia.getAlmoco() == null) {
				rservice.save(refeicao);
				dia.setAlmoco(refeicao);				
			}
			else {
				refeicao.setId(dia.getAlmoco().getId());
				rservice.save(refeicao);
				dia.setAlmoco(refeicao);
			}
			aservice.updateUltimaAtualizacao(dia.getAlmoco());
		}
		else if(dia.isJantaTime()) {
			if(dia.getJanta() == null) {
				rservice.save(refeicao);
				dia.setJanta(refeicao);				
			}
			else {
				refeicao.setId(dia.getJanta().getId());
				rservice.save(refeicao);
				dia.setJanta(refeicao);
			}
			aservice.updateUltimaAtualizacao(dia.getJanta());
		}
		return ResponseEntity.ok(service.save(dia));
	}
	
	
}
