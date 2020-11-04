package com.educandoweb.couse.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.couse.entities.Funcionario;
import com.educandoweb.couse.entities.Hierarquia;
import com.educandoweb.couse.entities.Pendencia;
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
	
//	@PostMapping (value = "/encontrarChefe")
//	public ResponseEntity<Hierarquia> filtrar (@RequestBody Long funcionario){
//		Hierarquia chefe = service.encontrarChefe(funcionario);
//		
//
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//				  .buildAndExpand(obj.getClass()).toUri();
//		return ResponseEntity.created(uri).body(chefe);
//	}
}
