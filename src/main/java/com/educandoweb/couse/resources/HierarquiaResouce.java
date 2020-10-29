package com.educandoweb.couse.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.couse.entities.Hierarquia;
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
	
//	@PostMapping
//	public ResponseEntity<Hierarquia> insert(@RequestBody Hierarquia obj){
//		obj = service.insert(obj);
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
//		return ResponseEntity.created(uri).body(obj);
//	}
	
	
//	@DeleteMapping(value = "/{id}")
//	public ResponseEntity<Void> delete(@PathVariable Long id){
//		service.delete(id);
//		return ResponseEntity.noContent().build();
//	}
	
	@GetMapping(value = "/{cpf}")
	public ResponseEntity<String> encontrarChefe(@Valid @PathVariable long cpf){
		String obj = service.encontrarChefe(cpf);
		return ResponseEntity.ok().body(obj);
	}
}
