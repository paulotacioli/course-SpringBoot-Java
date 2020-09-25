package com.educandoweb.couse.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.couse.entities.Regiao;
import com.educandoweb.couse.services.RegiaoService;

@RestController
@RequestMapping(value = "/regioes")
public class RegiaoResouce {

	@Autowired
	private RegiaoService service;
	
	@GetMapping
	public ResponseEntity<List<Regiao>> findAll(){
		List<Regiao> list = service.findAll();
		System.out.println(list.get(0).toString());
		System.out.println("ResponseEntity.ok().body(list);"+ResponseEntity.ok().body(list));
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Regiao> findById(@PathVariable Long id){
		Regiao obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
}
