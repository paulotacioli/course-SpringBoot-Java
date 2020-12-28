package com.educandoweb.couse.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.couse.entities.Acao;
import com.educandoweb.couse.entities.Comite;
import com.educandoweb.couse.entities.Objetivo;
import com.educandoweb.couse.repositores.AcaoRepository;
import com.educandoweb.couse.repositores.ObjetivoRepository;
import com.educandoweb.couse.services.exceptions.CampoJaExisteException;
import com.educandoweb.couse.services.exceptions.CampoVazioException;
import com.educandoweb.couse.services.exceptions.DatabaseException;
import com.educandoweb.couse.services.exceptions.ResourceNotFoundException;

@Service
public class ObjetivoService {
	
	@Autowired
	private ObjetivoRepository repository;
	@Autowired
	private AcaoRepository repositoryAcao;

	public List<Objetivo> findAll(){
		return repository.findAll();	

	}
	
	public Objetivo findById(Long id) {
		Optional<Objetivo> obj = repository.findById(id);
		return obj.get();
	}
	
	public Objetivo insert(Objetivo obj) {
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
			List<Acao> listAcao = new ArrayList<>();
			Objetivo obj = new Objetivo ();
			obj.setId(id);
			listAcao = repositoryAcao.findAllByObjetivo(obj);
			
			for (Acao acaoAtual: listAcao) {
			
				repositoryAcao.deleteById(acaoAtual.getId());

			}
	
			repository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public List<Objetivo> listarObjetivos(Long id) {
		List<Objetivo> obj = repository.findAllByComite_Id(id);
		return obj;
	}
}
