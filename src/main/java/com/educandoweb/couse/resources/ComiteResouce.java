package com.educandoweb.couse.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.couse.entities.Comite;
import com.educandoweb.couse.entities.Funcionario;
import com.educandoweb.couse.services.ComiteService;

@RestController
@RequestMapping(value = "/comites")
public class ComiteResouce {

	@Autowired
	private ComiteService service;
	
	@GetMapping
	public ResponseEntity<List<Comite>> findAll(){
		List<Comite> list = service.findAll();

		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Comite> findById(@PathVariable Long id){
		Comite obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Comite> insert(@RequestBody Comite obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/atualizar-foto")
	public boolean atualizarFotoComite (@RequestBody Comite obj){
		return service.atualizarFotoComite(obj);
	 
	}
	
	
	@PutMapping(value = "/atualizar-dados")
	public ResponseEntity<Comite> atualizarDadosComite (@RequestBody Comite obj){
		obj = service.atualizarComite(obj);
		return ResponseEntity.ok().body(obj);
	 
	}
}
