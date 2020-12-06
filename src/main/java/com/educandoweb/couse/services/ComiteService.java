package com.educandoweb.couse.services;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.couse.entities.Comite;
import com.educandoweb.couse.entities.Funcionario;
import com.educandoweb.couse.repositores.ComiteRepository;
import com.educandoweb.couse.services.exceptions.CampoJaExisteException;
import com.educandoweb.couse.services.exceptions.CampoVazioException;
import com.educandoweb.couse.services.exceptions.DatabaseException;
import com.educandoweb.couse.services.exceptions.ErroNaoMapeadoException;
import com.educandoweb.couse.services.exceptions.ResourceNotFoundException;

@Service
public class ComiteService {
	
	@Autowired
	private ComiteRepository repository;

	public List<Comite> findAll(){
		return repository.findAll();	

	}
	
	public Comite findById(Long id) {
		Optional<Comite> obj = repository.findById(id);
		return obj.get();
	}
	
	public Comite insert(Comite obj) {
		try {
			return repository.save(obj);
		} catch (NullPointerException e) {
			throw new CampoVazioException();
		} catch (ConstraintViolationException e) {
			throw new CampoVazioException();
		} catch (DataIntegrityViolationException e) {
			throw new CampoJaExisteException();
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
	
	public boolean atualizarFotoComite(Comite obj) {
		try {
			System.out.println("entrou 1");
		Comite entity = repository.getOne(obj.getId());
		entity.toString();
		System.out.println("entrou 2");
		entity.setUrlFoto(obj.getUrlFoto());
		System.out.println("entrou 3");
		System.out.println(obj.getId());
		System.out.println(obj.getUrlFoto());
		System.out.println(entity.toString());
		repository.save(entity);
		System.out.println("entrou 4");
		return true;
		} catch (RuntimeException e) {
			throw new ErroNaoMapeadoException("Erro não mapeado na aprovação de funcionarios.");
		}
	}
}
