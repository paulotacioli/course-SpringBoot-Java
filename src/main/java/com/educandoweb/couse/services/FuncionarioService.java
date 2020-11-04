package com.educandoweb.couse.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import com.educandoweb.couse.entities.Funcionario;
import com.educandoweb.couse.entities.Hierarquia;
import com.educandoweb.couse.repositores.FuncionarioRepository;
import com.educandoweb.couse.repositores.HierarquiaRepository;
import com.educandoweb.couse.repositores.PendenciaRepository;
import com.educandoweb.couse.services.exceptions.DatabaseException;
import com.educandoweb.couse.services.exceptions.ErroNaoMapeadoException;
import com.educandoweb.couse.services.exceptions.ReferenciaInexistenteException;
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
	
	@Autowired
	private HierarquiaRepository hierarquiaRepository;
	
	@Autowired
	private PendenciaRepository PendenciaRepository;


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
					System.out.println("Salvou no login service!");
					
					
					Hierarquia objHierarquia = new Hierarquia();
					System.out.println("chegou 1");
					objHierarquia.setComite(obj.getComite().getId());
					System.out.println("chegou 2");
					objHierarquia.setFuncionario(obj.getCpf());
					System.out.println("chegou 3");
					objHierarquia.setRelacionamento(obj.getHierarquia());
					System.out.println("chegou 4");
					hierarquiaRepository.save(objHierarquia);
					System.out.println("chegou 5");
					
					repository.save(objEcp);
					System.out.println("TERMINOU COM SUCESSOOOOOOOO!!!!!!!!");

								
				}catch (DataIntegrityViolationException e) {
//						e.printStackTrace();
					System.out.println("DEU MERDA!!");
				       throw new ReferenciaInexistenteException();
				       
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
						
						throw new ValidacaoTamanhoSenhaException ("A senha deve conter no maximo 6 caracteres!");
					} 
			}
					
		} else {
			throw new SenhasDiferentesException ("Senhas no cadastro de corretores nao estao iguais.");
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
	
	
	public Funcionario atualizarFuncionario(Funcionario obj){
		try {
		
			Funcionario entity = repository.findByCpf(obj.getCpf());
			entity.setNome(obj.getNome());
			entity.setEmail(obj.getEmail());
			entity.setDescricao(obj.getDescricao());
			entity.setFuncao(obj.getFuncao());
			entity.setCelular(obj.getCelular());


			return repository.save(entity);
			
	        
		} catch (TransactionSystemException e) {
			
			throw new ViolationException ("Existem campos vazios!", null);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException ("O recurso a ser aprovado nao existe na base. Atualize a pagina e tente novamente.");
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw new ErroNaoMapeadoException("Erro nao mapeado na aprovacao de funcionarios.");
		}
	}
	
	
//	public Funcionario findByNome(String nome) {
//		Optional<Funcionario> obj = repository.findByNome(nome);
//		return obj.orElseThrow(() -> new ResourceNotFoundException(nome));
//	}
}
