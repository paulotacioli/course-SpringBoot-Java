package com.educandoweb.couse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.couse.entities.Skills;
import com.educandoweb.couse.repositores.SkillsRepository;

@Service
public class SkillsService {
	
	@Autowired
	private SkillsRepository repository;

	public List<Skills> findAll(){
		return repository.findAll();	

	}
	
	public Skills findById(Long id) {
		Optional<Skills> obj = repository.findById(id);
		return obj.get();
	}
}
