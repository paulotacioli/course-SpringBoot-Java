package com.educandoweb.couse.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.couse.entities.Hierarquia;
import com.educandoweb.couse.entities.Time;
import com.educandoweb.couse.services.HierarquiaService;

@RestController
@RequestMapping(value = "/hierarquias")
public class HierarquiaResouce {

	@Autowired
	private HierarquiaService service;
	
	@GetMapping
	public ResponseEntity<List<Hierarquia>> findAll(){
		List<Hierarquia> list = service.findAll();

		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping (value = "/encontrar-lider/{cpf}")
	public ResponseEntity<Time> encontrar (@PathVariable Long cpf){
		Time chefe = service.encontrarChefe(cpf);
		return ResponseEntity.ok().body(chefe);
	}
	
	@GetMapping (value = "/encontrar-time/{cpf}")
	public ResponseEntity<List<Time>> encontrarTime (@PathVariable Long cpf){
		List<Time> time = service.encontrarTime(cpf);
		return ResponseEntity.ok().body(time);
	}
}
