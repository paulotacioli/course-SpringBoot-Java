package com.educandoweb.couse.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import com.educandoweb.couse.entities.Comite;
import com.educandoweb.couse.entities.Funcionario;
import com.educandoweb.couse.repositores.ComiteRepository;
import com.educandoweb.couse.services.exceptions.CampoJaExisteException;
import com.educandoweb.couse.services.exceptions.CampoVazioException;
import com.educandoweb.couse.services.exceptions.DatabaseException;
import com.educandoweb.couse.services.exceptions.ErroNaoMapeadoException;
import com.educandoweb.couse.services.exceptions.ResourceNotFoundException;
import com.educandoweb.couse.services.exceptions.ViolationException;

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
		Comite entity = repository.getOne(obj.getId());
		entity.toString();
		entity.setUrlFoto(obj.getUrlFoto());
		repository.save(entity);
		return true;
		} catch (RuntimeException e) {
			throw new ErroNaoMapeadoException("Erro não mapeado na aprovação de funcionarios.");
		}
	}
	
	public Comite atualizarComite(Comite obj) {
		try {

			Comite entity = repository.findById((long) obj.getId());
			entity.setComite(obj.getComite());
			entity.setDescricao(obj.getDescricao());
			entity.setDataCriacao(obj.getDataCriacao());
			entity.setRegiao(obj.getRegiao());

			
			return repository.save(entity);

		} catch (TransactionSystemException e) {

			throw new ViolationException("Existem campos vazios!", null);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(
					"O recurso a ser aprovado nao existe na base. Atualize a pagina e tente novamente.");
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw new ErroNaoMapeadoException("Erro não mapeado. Favor tentar mais tarde, em caso de persistencia contatar a equipe de TI.");
		}
	}

}
