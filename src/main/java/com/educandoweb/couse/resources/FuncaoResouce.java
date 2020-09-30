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

import com.educandoweb.couse.entities.Funcao;
import com.educandoweb.couse.services.FuncaoService;

@RestController
@RequestMapping(value = "/funcoes")
public class FuncaoResouce {

	@Autowired
	private FuncaoService service;
	
	@GetMapping
	public ResponseEntity<List<Funcao>> findAll(){
		List<Funcao> list = service.findAll();
		System.out.println(list.get(0).toString());
		System.out.println("ResponseEntity.ok().body(list);"+ResponseEntity.ok().body(list));
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Funcao> findById(@PathVariable Long id){
		Funcao obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	@PostMapping
	public ResponseEntity<Funcao> insert(@RequestBody Funcao obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
	
}
