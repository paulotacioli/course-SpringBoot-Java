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

import com.educandoweb.couse.entities.Funcionario;
import com.educandoweb.couse.entities.Skills;
import com.educandoweb.couse.services.SkillsService;

@RestController
@RequestMapping(value = "/skills")
public class SkillsResouce {

	@Autowired
	private SkillsService service;
	
	@GetMapping
	public ResponseEntity<List<Skills>> findAll(){
		List<Skills> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Skills> findById(@PathVariable Long id){
		Skills obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	

	
	@PostMapping
	public ResponseEntity<Skills> insert(@RequestBody Skills obj){
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
