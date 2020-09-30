package com.educandoweb.couse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.couse.entities.Funcao;
import com.educandoweb.couse.repositores.FuncaoRepository;

@Service
public class FuncaoService {
	
	@Autowired
	private FuncaoRepository repository;

	public List<Funcao> findAll(){
		return repository.findAll();	

	}
	
	public Funcao findById(Long id) {
		Optional<Funcao> obj = repository.findById(id);
		return obj.get();
	}

	public Funcao insert(Funcao obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
