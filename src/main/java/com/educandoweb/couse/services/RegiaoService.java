package com.educandoweb.couse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.couse.entities.Regiao;
import com.educandoweb.couse.entities.Skills;
import com.educandoweb.couse.repositores.RegiaoRepository;

@Service
public class RegiaoService {
	
	@Autowired
	private RegiaoRepository repository;

	public List<Regiao> findAll(){
		return repository.findAll();	

	}
	
	public Regiao findById(Long id) {
		Optional<Regiao> obj = repository.findById(id);
		return obj.get();
	}
	
	public Regiao insert(Regiao obj) {
		return repository.save(obj);
	}
}
