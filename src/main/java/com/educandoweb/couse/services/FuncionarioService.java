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
import com.educandoweb.couse.services.exceptions.JaTemCoordenadorHierarquiaException;
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
			entity.setCelular(obj.getCelular());
			entity.setDataNascimento(obj.getDataNascimento());
			entity.setDataCadastro(obj.getDataCadastro());
			entity.setRegiao(obj.getRegiao());

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
	
	public Funcionario atualizarSkill(Funcionario obj) {
		try {

			Funcionario entity = repository.findByCpf(obj.getCpf());
			if (obj.getSkill().size() >= 1) {
				entity.setSkill(obj.getSkill());
			}

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
		try{
			System.out.println('1');


		List<Hierarquia> hierarquia = new ArrayList<Hierarquia>();
		hierarquia = hierarquiaRepository.findAllByFuncionario(obj.getCpf());
		if (hierarquia.size() == 0) {
			System.out.println('2');

			if (obj.getCoordenador() == 1) {
				System.out.println('3');

				if (obj.getComite().getId() != 2 && obj.getComite().getId() != 1) {
					System.out.println('4');

					// inserir ele como subordinado do comite 2
					Hierarquia objHierarquia = new Hierarquia();
					objHierarquia.setComite((long) 2);
					objHierarquia.setFuncionario(obj.getCpf());
					objHierarquia.setRelacionamento('s');
					
					System.out.println('5');

					// inserir ele como coordenador do obj.getComite()
					//verificar se ja existe cooredenador do obj.getComite()
					Hierarquia verificacao = hierarquiaRepository.findByComiteAndRelacionamento(obj.getComite().getId(), 'c');
					if (verificacao != null) {
						System.out.println('6');

						throw new JaTemCoordenadorHierarquiaException();
					} 
					Hierarquia objHierarquia2 = new Hierarquia();
					objHierarquia2.setComite(obj.getComite().getId());
					objHierarquia2.setFuncionario(obj.getCpf());
					objHierarquia2.setRelacionamento('c');
					hierarquiaRepository.save(objHierarquia);
					hierarquiaRepository.save(objHierarquia2);
					return true;

				} else {
					System.out.println('7');

					if (obj.getComite().getId() == 1) {
						// inserir ele como coordenador do comite presidencial
						Hierarquia verificacao = hierarquiaRepository.findByComiteAndRelacionamento((long)1, 'c');
						if (verificacao != null) {
							System.out.println('0');

							throw new JaTemCoordenadorHierarquiaException();
						} 
						System.out.println('8');

						Hierarquia objHierarquia = new Hierarquia();
						objHierarquia.setComite(obj.getComite().getId());
						objHierarquia.setFuncionario(obj.getCpf());
						objHierarquia.setRelacionamento('c');
						hierarquiaRepository.save(objHierarquia);
						return true;

					} else {
						System.out.println('9');

						// ele é do comite>2
						// inserir ele como coordenador do comite obj.getComite
						// inserir ele como subordinado do comite 1
						Hierarquia verificacao = hierarquiaRepository.findByComiteAndRelacionamento((long)2, 'c');
						if (verificacao != null) {
							

							throw new JaTemCoordenadorHierarquiaException();
						} 		
						System.out.println('a');

						Hierarquia objHierarquia = new Hierarquia();
						objHierarquia.setComite(obj.getComite().getId());
						objHierarquia.setFuncionario(obj.getCpf());
						objHierarquia.setRelacionamento('c');
					
						System.out.println('d');

						Hierarquia objHierarquia2 = new Hierarquia();
						objHierarquia2.setComite((long) 1);
						objHierarquia2.setFuncionario(obj.getCpf());
						objHierarquia2.setRelacionamento('s');
						hierarquiaRepository.save(objHierarquia2);
						hierarquiaRepository.save(objHierarquia);
						return true;

					}

				}

			} else {
				// inserir ele como subordinado do comite obj.getComite()
				System.out.println('e');

				Hierarquia objHierarquia = new Hierarquia();
				objHierarquia.setComite(obj.getComite().getId());
				objHierarquia.setFuncionario(obj.getCpf());
				objHierarquia.setRelacionamento('s');
				hierarquiaRepository.save(objHierarquia);
				return true;
			
			}
		} else {
			System.out.println('3');

			throw new FalhaPermissaoHierarquiaException();
		}
		} catch (FalhaPermissaoHierarquiaException e) {
			throw new FalhaPermissaoHierarquiaException();

		} catch (JaTemCoordenadorHierarquiaException e) {
			throw new JaTemCoordenadorHierarquiaException();
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
		} catch (JaTemCoordenadorHierarquiaException e) {
			throw new JaTemCoordenadorHierarquiaException();

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
	
	public List<Funcionario> filterFuncionario(Funcionario obj) {
		long maxComite = 1000;
		long minComite = 0;
		
		long maxRegiao = 100000;
		long minRegiao = 0;
		
		long maxFuncao = 1000;
		long minFuncao = 0;
		
		long maxSkill = 1000;
		long minSkill = 0;

		
		if (obj.getComite() != null) {
			maxComite = obj.getComite().getId() +1;
			minComite = obj.getComite().getId() -1;
		}
		
		if (obj.getRegiao() != null) {
			maxRegiao = obj.getRegiao().getId() +1;
			minRegiao = obj.getRegiao().getId() -1;
		}
		
		if (obj.getFuncao() != null) {
			maxFuncao = obj.getFuncao().getId() +1;
			minFuncao = obj.getFuncao().getId() -1;
		}
		
		if (obj.getSkill() != null) {
			maxSkill = obj.getSkill().iterator().next().getId() +1;
			minSkill = obj.getSkill().iterator().next().getId() -1;
		}
		
		List<Funcionario> objFuncionario = repository.findAllByComite_IdGreaterThanAndComite_IdLessThanAndRegiao_IdGreaterThanAndRegiao_IdLessThanAndFuncao_IdGreaterThanAndFuncao_IdLessThanAndSkill_IdGreaterThanAndSkill_IdLessThan(minComite, maxComite, minRegiao, maxRegiao, minFuncao, maxFuncao, minSkill, maxSkill);
		
		//List<Funcionario> objFuncionario = repository.findAllBySkillInAndFuncaoAndRegiaoAndAprovadoAndCpfAndComite(obj.getSkill(), obj.getFuncao(), obj.getRegiao(), obj.getAprovado(), obj.getCpf(), obj.getComite());
		return objFuncionario;
	}
	
	
}
