package com.educandoweb.couse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.educandoweb.couse.entities.Funcionario;
import com.educandoweb.couse.entities.Login;
import com.educandoweb.couse.repositores.LoginRepository;
import com.educandoweb.couse.services.exceptions.DatabaseException;
import com.educandoweb.couse.services.exceptions.ResourceNotFoundException;

@Service
public class LoginService implements UserDetailsService {
	
	@Autowired
	private LoginRepository repository;

	public List<Login> findAll(){
		return repository.findAll();	

	}
	
	public Login findById(Long id) {
		Optional<Login> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Login insert(Login obj) {
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
	
	public Login findByCpf(Long cpf) {
		Optional<Login> obj = repository.findById(cpf);

		return obj.orElseThrow(() -> new ResourceNotFoundException(cpf));
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("ENTROU 333333333333333333333");

		  Login login = findByCpf(Long.parseLong(username));
		  
		  
		  System.out.println("TEMOS 40:"+ login.getId().toString());

		    UserBuilder builder = null;
		    if (login != null) {
		      builder = org.springframework.security.core.userdetails.User.withUsername(username);
		      builder.password(login.getSenha());
		      if(login.getAprovado() == 2 && login.getFuncionario().getCoordenador() == 1) {
			      builder.roles("COORDENADOR");
		      } else if (login.getAprovado() == 8) {
		    	  builder.roles("ADMINISTRADOR");
		      } else if (login.getAprovado() == 1 && login.getFuncionario().getCoordenador() == 0) {
		    	  builder.roles("FUNCIONARIO"); 
		      } else if (login.getAprovado() == 3) {
			    	  builder.roles("REPROVADO"); 
		      } else {
		    	  builder.roles("PENDENTE");
		      }
		    } else {
		      throw new UsernameNotFoundException("User not found.");
		    }
		    
		    System.out.println("TEMOS 41:"+ builder.toString());
		    System.out.println("TEMOS 41:"+ builder.build().toString());

		    return builder.build();
		  }
	
		public void saveLoginFuncionario(Funcionario obj) {
			Login login = new Login();
				login.setFuncionario(obj);
				login.setSenha(obj.getSenha());
				login.setAprovado(obj.getAprovado());
				
				System.out.println("hola amigos:"+ obj.getAprovado());
				//login.setAprovado(obj.getAprovado());
				
				repository.save(login);
			}
		
	}
