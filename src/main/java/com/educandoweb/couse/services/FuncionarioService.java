package com.educandoweb.couse.services;

import java.util.ArrayList;
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
import com.educandoweb.couse.entities.Login;
import com.educandoweb.couse.repositores.FuncionarioRepository;
import com.educandoweb.couse.repositores.HierarquiaRepository;
import com.educandoweb.couse.repositores.LoginRepository;
import com.educandoweb.couse.repositores.PendenciaRepository;
import com.educandoweb.couse.services.exceptions.DatabaseException;
import com.educandoweb.couse.services.exceptions.ErroNaoMapeadoException;
import com.educandoweb.couse.services.exceptions.FalhaPermissaoHierarquiaException;
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
	private LoginRepository loginRepository;

	@Autowired
	private HierarquiaRepository hierarquiaRepository;

	@Autowired
	private PendenciaRepository PendenciaRepository;

	public List<Funcionario> findAll() {
		return repository.findAll();

	}

	public Funcionario findById(Long id) {
		Optional<Funcionario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Funcionario insert(Funcionario obj) {

		if (obj.getSenha().toString().equals(obj.getSenhaConfirm().toString())) {

			if (repository.existsById(obj.getCpf())) {

				throw new DatabaseException(obj.getCpf().toString());
			} else {

				if (obj.getSenha().length() > 5) {

					try {

						Funcionario objEcp = new Funcionario();

						objEcp = obj;
						objEcp.setSenha(new BCryptPasswordEncoder().encode(obj.getSenha()));
						objEcp.setSenhaConfirm(new BCryptPasswordEncoder().encode(obj.getSenhaConfirm()));
						obj.setAprovado(0);
						obj.setCoordenador(0);
						loginService.saveLoginFuncionario(objEcp);
						System.out.println("Salvou no login service!");

						repository.save(obj);

						repository.save(objEcp);

					} catch (DataIntegrityViolationException e) {
//						e.printStackTrace();
						System.out.println("DEU MERDA!!");
						throw new ReferenciaInexistenteException();

					} catch (TransactionSystemException e) {
						System.out.println("2");

						e.printStackTrace();
						e.getCause().getStackTrace();
						throw new ViolationException(obj, e.getMostSpecificCause().toString());
					} catch (JpaSystemException e) {
						System.out.println("3");

						e.printStackTrace();
						throw new ViolationException(obj, e.getMostSpecificCause().toString());
					} catch (RuntimeException e) {
						e.printStackTrace();
					}
				} else {

					throw new ValidacaoTamanhoSenhaException("A senha deve conter no maximo 6 caracteres!");
				}
			}

		} else {
			throw new SenhasDiferentesException("Senhas no cadastro de corretores nao estao iguais.");
		}

		return obj;
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public boolean atualizarFoto (Funcionario obj) {
		try {
			Funcionario entity = repository.getOne(obj.getCpf());

		entity.setUrlFoto(obj.getUrlFoto());
		repository.save(entity);
		return true;
		} catch (RuntimeException e) {
			throw new ErroNaoMapeadoException("Erro não mapeado na aprovação de funcionarios.");
		}

	}
	public boolean atualizarCurriculo (Funcionario obj) {
		try {
			Funcionario entity = repository.getOne(obj.getCpf());

		entity.setUrlCurriculo(obj.getUrlCurriculo());
		repository.save(entity);
		return true;
		} catch (RuntimeException e) {
			throw new ErroNaoMapeadoException("Erro não mapeado na aprovação de funcionarios.");
		}

	}


	public Funcionario atualizarFuncionario(Funcionario obj) {
		try {

			Funcionario entity = repository.findByCpf(obj.getCpf());
			entity.setNome(obj.getNome());
			entity.setEmail(obj.getEmail());
			entity.setDescricao(obj.getDescricao());
			entity.setFuncao(obj.getFuncao());
			entity.setCelular(obj.getCelular());

			return repository.save(entity);

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

	public boolean inserirHierarquia(Funcionario obj) {

		List<Hierarquia> hierarquia = new ArrayList<Hierarquia>();
		hierarquia = hierarquiaRepository.findAllByFuncionario(obj.getCpf());
		if (hierarquia.size() == 0) {

			if (obj.getCoordenador() == 1) {
				if (obj.getComite().getId() != 2 && obj.getComite().getId() != 1) {

					// inserir ele como subordinado do comite 2
					Hierarquia objHierarquia = new Hierarquia();
					objHierarquia.setComite((long) 2);
					objHierarquia.setFuncionario(obj.getCpf());
					objHierarquia.setRelacionamento('s');
					hierarquiaRepository.save(objHierarquia);

					// inserir ele como coordenador do obj.getComite()
					Hierarquia objHierarquia2 = new Hierarquia();
					objHierarquia2.setComite(obj.getComite().getId());
					objHierarquia2.setFuncionario(obj.getCpf());
					objHierarquia2.setRelacionamento('c');
					hierarquiaRepository.save(objHierarquia2);
					return true;

				} else {
					if (obj.getComite().getId() == 1) {
						// inserir ele como coordenador do comite presidencial
						Hierarquia objHierarquia = new Hierarquia();
						objHierarquia.setComite(obj.getComite().getId());
						objHierarquia.setFuncionario(obj.getCpf());
						objHierarquia.setRelacionamento('c');
						hierarquiaRepository.save(objHierarquia);
						return true;

					} else {
						// ele é do comite>2
						// inserir ele como coordenador do comite obj.getComite
						// inserir ele como subordinado do comite 1

						Hierarquia objHierarquia = new Hierarquia();
						objHierarquia.setComite(obj.getComite().getId());
						objHierarquia.setFuncionario(obj.getCpf());
						objHierarquia.setRelacionamento('c');
						hierarquiaRepository.save(objHierarquia);

						Hierarquia objHierarquia2 = new Hierarquia();
						objHierarquia2.setComite((long) 1);
						objHierarquia2.setFuncionario(obj.getCpf());
						objHierarquia2.setRelacionamento('s');
						hierarquiaRepository.save(objHierarquia2);
						return true;

					}

				}

			} else {
				// inserir ele como subordinado do comite obj.getComite()
				Hierarquia objHierarquia = new Hierarquia();
				objHierarquia.setComite(obj.getComite().getId());
				objHierarquia.setFuncionario(obj.getCpf());
				objHierarquia.setRelacionamento('s');
				hierarquiaRepository.save(objHierarquia);
				return true;

			}
		} else {
			throw new FalhaPermissaoHierarquiaException();
		}

	}

	public List<Funcionario> aprovarFuncionarios(List<Funcionario> obj) {
		
		
		
		
		
		try {
		
			
			for (Funcionario funcionarioAtual : obj) {
				//ATUALIZA O COORDENADOR DO FUNCIONARIO E SALVA NA TABELA
				Funcionario entityCoordenador = repository.getOne(funcionarioAtual.getCpf());
				if (funcionarioAtual.getAprovado() == 2) {
					entityCoordenador.setCoordenador(1);
				} else {
					entityCoordenador.setCoordenador(0);

				}
				repository.save(entityCoordenador);

				//APROVAÇÃO
				Funcionario entity = repository.getOne(funcionarioAtual.getCpf());
				Login loginEntity = loginRepository.getOne(funcionarioAtual.getCpf());
				entity.setAprovado(funcionarioAtual.getAprovado());
				
				loginEntity.setAprovado(funcionarioAtual.getAprovado());
				Funcionario func = new Funcionario();
				func = repository.findByCpf(funcionarioAtual.getCpf());
				if (funcionarioAtual.getAprovado() == 1 || funcionarioAtual.getAprovado() == 2) {
				
					inserirHierarquia(func);
				}
				
		

				repository.save(entity);

			}

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(
					"O recurso a ser aprovado não existe na base. Atualize a página e tente novamente.");
		} catch (FalhaPermissaoHierarquiaException e) {
			throw new FalhaPermissaoHierarquiaException();

		} catch (RuntimeException e) {
			throw new ErroNaoMapeadoException("Erro não mapeado na aprovação de funcionarios.");
		}
		return obj;
	}

	public List<Funcionario> findPendentes() {
		try {
			List<Funcionario> obj = repository.findAllByAprovado(0);
			return obj;
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;

	}
}
