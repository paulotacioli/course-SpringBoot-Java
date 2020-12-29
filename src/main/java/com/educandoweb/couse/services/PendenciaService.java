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

import com.educandoweb.couse.entities.Funcionario;
import com.educandoweb.couse.entities.Pendencia;
import com.educandoweb.couse.repositores.PendenciaRepository;
import com.educandoweb.couse.services.exceptions.CampoJaExisteException;
import com.educandoweb.couse.services.exceptions.CampoVazioException;
import com.educandoweb.couse.services.exceptions.DatabaseException;
import com.educandoweb.couse.services.exceptions.ErroNaoMapeadoException;
import com.educandoweb.couse.services.exceptions.ResourceNotFoundException;
import com.educandoweb.couse.services.exceptions.ViolationException;

@Service
public class PendenciaService {
	
	@Autowired
	private PendenciaRepository pendenciaRepository;

	public List<Pendencia> findAll(){
		return pendenciaRepository.findAll();

	}
	
	public Pendencia findById(Long id) {
		Optional<Pendencia> obj = pendenciaRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Pendencia insert(Pendencia obj) {
		try {
			return pendenciaRepository.save(obj);
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
			pendenciaRepository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public List<Pendencia> findPendenciaByFuncionario(Funcionario obj) {
		List<Pendencia> objPendencias = pendenciaRepository.findAllByFuncionario(obj);
		return objPendencias;
	}
	
	
	
	public Pendencia atualizarStatus(Pendencia obj) {
		try {

			Pendencia entity = pendenciaRepository.getOne(obj.getId());
			entity.setStatus(obj.getStatus());
	
			return pendenciaRepository.save(entity);
			
	
		} catch (TransactionSystemException e) {

			throw new ViolationException("Existem campos vazios!", null);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(
					"O recurso a ser aprovado nao existe na base. Atualize a pagina e tente novamente.");
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw new ErroNaoMapeadoException("Erro nao mapeado na aprovacao de funcionarios.");
		}
	}
}
