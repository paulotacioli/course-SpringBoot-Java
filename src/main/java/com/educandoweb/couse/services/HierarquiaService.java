package com.educandoweb.couse.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.couse.entities.Comite;
import com.educandoweb.couse.entities.Funcionario;
import com.educandoweb.couse.entities.Hierarquia;
import com.educandoweb.couse.entities.Time;
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

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	public List<Hierarquia> findAll() {
		return repository.findAll();

	}

	public Hierarquia findById(Long funcionario) {
		Optional<Hierarquia> obj = repository.findById(funcionario);
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

	public void delete(Long funcionario) {
		try {
			repository.deleteById(funcionario);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(funcionario);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Time encontrarChefe(Long cpf) {
		Funcionario objFunc = new Funcionario();
		Hierarquia objChefe = new Hierarquia();
		if (repository.findByFuncionarioAndRelacionamento(cpf, 's') != null) {
			objChefe = repository.findByFuncionarioAndRelacionamento(cpf, 's');
			System.out.println("imprimir o comite dessa pessoa" + objChefe.getComite());
			Hierarquia objNovo = new Hierarquia();
			objNovo = repository.findByComiteAndRelacionamento(objChefe.getComite(), 'c');
			System.out.println("imprmir o cpf do chefe" + objNovo.getFuncionario());
			objFunc = funcionarioRepository.findByCpf(objNovo.getFuncionario());
			Time objTime = new Time();
			objTime.setCpf(objNovo.getFuncionario());
			objTime.setNome(objFunc.getNome());
			return objTime;
		} else {
			return null;
		}
	}

	public List<Time> encontrarTime(Long cpf) {
		Funcionario objFunc = new Funcionario();
		objFunc = funcionarioRepository.findByCpf(cpf);
		// não é chefe
		List<Hierarquia> list = new ArrayList<Hierarquia>();
		list = repository.findAllByComiteAndRelacionamento(objFunc.getComite().getId(), 's');
		List<Time> timeLista = new ArrayList<Time>();
		for (Hierarquia objAtual : list) {
			Time objTime = new Time();
			objTime.setCpf(objAtual.getFuncionario());
			objTime.setNome(funcionarioRepository.findByCpf(objAtual.getFuncionario()).getNome());
			
			if (!cpf.toString().equals(objAtual.getFuncionario().toString())) {
				timeLista.add(objTime);
			}
		}

		return timeLista;
	}
	
	public Time encontrarCoordenador(Long id) {
		Funcionario objFunc = new Funcionario();
		Hierarquia objNovo = new Hierarquia();
		if ((objNovo = repository.findByComiteAndRelacionamento(id, 'c')) != null) {
			try {
				System.out.println("imprmir o cpf do chefe: " + objNovo.getFuncionario());
				objFunc = funcionarioRepository.findByCpf(objNovo.getFuncionario());
				Time objTime = new Time();
				objTime.setCpf(objNovo.getFuncionario());

				objTime.setNome(objFunc.getNome());

				return objTime;

			} catch (NullPointerException e) {
				throw new ResourceNotFoundException(e);
			}
		} else {
			return null;
		}
	}
	
	
	
//	public List<Time> encontrarIntegrantes(Long id) {
//		Funcionario objFunc = new Funcionario();
//		Hierarquia objNovo = new Hierarquia();
//			try {
//				List<Hierarquia> list = new ArrayList<Hierarquia>();
//				list = repository.findAllByComiteAndRelacionamento(id, 's');
//				objFunc = repository.findByCpf(list.getFuncionario());
//				
//				List<Time> timeLista = new ArrayList<Time>();
//				for (Hierarquia objAtual : list) {
//					System.out.println("entrou no for");
//					Time objTime = new Time();
//					System.out.println("chegou 1");
//					objTime.setCpf(objAtual.getFuncionario());
//
//					System.out.println("chegou 2");
//					objTime.setNome(funcionarioRepository.findByCpf(objAtual.getFuncionario()).getNome());
//					timeLista.add(objTime);
//					
//					System.out.println("chegou 3");
//				}
//				
//				System.out.println("saiu do for");
//				return timeLista;
//				
//			} catch (RuntimeException e) {
//				throw new CampoVazioException();
//			}
//	}
}
