package com.educandoweb.couse.services;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.couse.entities.Funcionario;
import com.educandoweb.couse.entities.Pendencia;
import com.educandoweb.couse.entities.Skills;
import com.educandoweb.couse.repositores.SkillsRepository;
import com.educandoweb.couse.services.exceptions.CampoVazioException;
import com.educandoweb.couse.services.exceptions.DatabaseException;
import com.educandoweb.couse.services.exceptions.ResourceNotFoundException;

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
	
	public Skills insert(Skills obj) {
		try {
		return repository.save(obj);
	}catch (ConstraintViolationException e) {
		e.printStackTrace();
		throw new CampoVazioException();
	}catch (DataIntegrityViolationException e) {
		throw new DatabaseException(null);
	}
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
