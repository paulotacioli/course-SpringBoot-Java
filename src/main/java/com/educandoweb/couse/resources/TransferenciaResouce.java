package com.educandoweb.couse.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.couse.entities.Funcionario;
import com.educandoweb.couse.entities.Transferencia;
import com.educandoweb.couse.services.TransferenciaService;

@RestController
@RequestMapping(value = "/transferencias")
public class TransferenciaResouce {

	@Autowired
	private TransferenciaService service;
	
	@GetMapping
	public ResponseEntity<List<Transferencia>> findAll(){
		List<Transferencia> list = service.findAll();

		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	public ResponseEntity<Transferencia> transferirFuncionario (@RequestBody Transferencia obj){
		obj = service.transferirFuncionario(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(1).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
}
