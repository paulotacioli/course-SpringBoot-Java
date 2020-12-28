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

import com.educandoweb.couse.entities.Funcionario;
import com.educandoweb.couse.entities.Pendencia;
import com.educandoweb.couse.services.PendenciaService;

@RestController
@RequestMapping(value = "/pendencias")
public class PendenciaResouce {

	@Autowired
	private PendenciaService service;
	
	@GetMapping
	public ResponseEntity<List<Pendencia>> findAll(){
		List<Pendencia> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Pendencia> findById(@PathVariable Long id){
		Pendencia obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Pendencia> insert(@RequestBody Pendencia obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	@PostMapping (value = "/funcionario")
	public ResponseEntity<List<Pendencia>> filtrar (@RequestBody Funcionario obj){
		System.out.println(obj.toString());
		List<Pendencia> listaPendencias = service.findPendenciaByFuncionario(obj);
		

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				  .buildAndExpand(obj.getClass()).toUri();
		return ResponseEntity.created(uri).body(listaPendencias);
	}
	
	
	@PutMapping(value = "/status")
	public boolean atualizarStatus (@RequestBody Pendencia obj){
		return service.atualizarStatus(obj);
	 
	}
}
