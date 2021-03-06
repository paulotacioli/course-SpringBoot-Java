package com.educandoweb.couse.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.couse.entities.Acao;
import com.educandoweb.couse.entities.Objetivo;
import com.educandoweb.couse.services.AcaoService;

@RestController
@RequestMapping(value = "/acoes")
public class AcaoResouce {

	@Autowired
	private AcaoService service;
	
	@GetMapping
	public ResponseEntity<List<Acao>> findAll(){
		List<Acao> list = service.findAll();

		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Acao> findById(@PathVariable Long id){
		Acao obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Acao> insert(@RequestBody Acao obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/objetivo/{id}")
	public ResponseEntity<List<Acao>> listarAcoes(@PathVariable Long id){
		List<Acao> obj = service.listarAcoes(id);
		return ResponseEntity.ok().body(obj);
	}
}
