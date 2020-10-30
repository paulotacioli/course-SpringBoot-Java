package com.educandoweb.couse.services;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.couse.entities.Funcionario;
import com.educandoweb.couse.entities.Hierarquia;
import com.educandoweb.couse.repositores.FuncionarioRepository;
import com.educandoweb.couse.repositores.HierarquiaRepository;
import com.educandoweb.couse.services.exceptions.CampoJaExisteException;
import com.educandoweb.couse.services.exceptions.CampoVazioException;
import com.educandoweb.couse.services.exceptions.DatabaseException;
import com.educandoweb.couse.services.exceptions.ResourceNotFoundException;

@Service
public class HierarquiaService {
	
	@Autowired
	private HierarquiaRepository repository;

	public List<Hierarquia> findAll(){
		return repository.findAll();	

	}
	
	public Hierarquia findById(Long id) {
		Optional<Hierarquia> obj = repository.findById(id);
		return obj.get();
	}
	
	public Hierarquia insert(Hierarquia obj) {
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

//	public String encontrarChefe(Long cpf){
//
//		Hierarquia obj = new  Hierarquia();
//		obj = HierarquiaRepository.findByFuncionarioAndRelacionamento(cpf,'s');
//		System.out.println("imprimir o comite dessa pessoa" + obj.getComite());
//		Hierarquia objNovo = new Hierarquia();
//		objNovo = HierarquiaRepository.findByComiteAndRelacionamento(obj.getComite(),'c');
//		System.out.println("imprmir o cpf do funcionario"+ objNovo.getFuncionario());
//		Optional<Funcionario> objFunc = Optional.of(new Funcionario());
//		objFunc = FuncionarioRepository.findById(objNovo.getFuncionario());
//		return objFunc;
//	}
}
