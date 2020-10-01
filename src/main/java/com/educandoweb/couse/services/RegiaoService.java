package com.educandoweb.couse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.couse.entities.Regiao;
import com.educandoweb.couse.repositores.RegiaoRepository;
import com.educandoweb.couse.services.exceptions.DatabaseException;
import com.educandoweb.couse.services.exceptions.ResourceNotFoundException;

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
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
}
