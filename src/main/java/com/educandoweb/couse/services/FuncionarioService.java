package com.educandoweb.couse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import com.educandoweb.couse.entities.Funcionario;
import com.educandoweb.couse.repositores.FuncionarioRepository;
import com.educandoweb.couse.services.exceptions.DatabaseException;
import com.educandoweb.couse.services.exceptions.ResourceNotFoundException;
import com.educandoweb.couse.services.exceptions.SenhasDiferentesException;
import com.educandoweb.couse.services.exceptions.ValidacaoTamanhoSenhaException;
import com.educandoweb.couse.services.exceptions.ViolationException;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository repository;
	
	@Autowired
	private LoginService loginService;

	public List<Funcionario> findAll(){
		return repository.findAll();	

	}
	
	public Funcionario findById(Long id) {
		Optional<Funcionario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Funcionario insert(Funcionario obj) {
		
		if (obj.getSenha().toString().equals(obj.getSenhaConfirm().toString())) {
			System.out.println("FUNCIONARIO 1111111111111111");

			if(repository.existsById(obj.getCpf())){
				System.out.println("FUNCIONARIO 2222222222222222");

				throw new DatabaseException(obj.getCpf().toString());
			} else {
				System.out.println("FUNCIONARIO 333333333333333333333");

				if (obj.getSenha().length() > 5) {
					System.out.println("FUNCIONARIO 4444444444444444444444444");

				try {
					System.out.println("FUNCIONARIO 55555555555555555555555");

					Funcionario objEcp = new Funcionario ();
					System.out.println("FUNCIONARIO 66666666666666666666666");

					objEcp = obj;
					objEcp.setSenha(new BCryptPasswordEncoder().encode(obj.getSenha()));
					objEcp.setSenhaConfirm(new BCryptPasswordEncoder().encode(obj.getSenhaConfirm()));	
					obj.setAprovado(8);
					System.out.println("FUNCIONARIO 9999999999999999999999");

					
					loginService.saveLoginFuncionario(objEcp);
					System.out.println("FUNCIONARIO 77777777777777777777777777");

					repository.save(objEcp);
					System.out.println("FUNCIONARIO 888888888888888888888888");

								
				}catch (DataIntegrityViolationException e) {
						e.printStackTrace();
				       throw new DatabaseException(obj.getCpf().toString());
				       
					} catch (TransactionSystemException e) {
						System.out.println("2");

						e.printStackTrace();	
						e.getCause().getStackTrace();
						throw new ViolationException (obj, e.getMostSpecificCause().toString());
					} catch (JpaSystemException e) {
						System.out.println("3");

						e.printStackTrace();
						throw new ViolationException (obj, e.getMostSpecificCause().toString());
					} catch (RuntimeException e) {
						e.printStackTrace();
					}
					} else {
						
						throw new ValidacaoTamanhoSenhaException ("A senha deve conter no mÃ­nimo 6 caracteres!");
					} 
			}
					
		} else {
			throw new SenhasDiferentesException ("Senhas no cadastro de corretores nÃ£o estÃ£o iguais.");
		}
			
			return obj;
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
}//
