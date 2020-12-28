package com.educandoweb.couse.repositores;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.couse.entities.Comite;
import com.educandoweb.couse.entities.Funcao;
import com.educandoweb.couse.entities.Funcionario;
import com.educandoweb.couse.entities.FuncionarioPesquisa;
import com.educandoweb.couse.entities.Pendencia;
import com.educandoweb.couse.entities.Regiao;
import com.educandoweb.couse.entities.Skills;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

	boolean existsById(Long cpf);

	Funcionario findByCpf(Long cpf); 

	List<Pendencia> findAllByCpf(Long cpf);

	List<Funcionario> findAllByAprovado(int aprovado);
	
	List<Funcionario> findAllBySkillInAndFuncaoAndRegiaoAndAprovadoAndCpfAndComite(Set<Skills> skill, Funcao funcao, Regiao regiao, int aprovado, Long cpf, Comite comite);

	List<Funcionario> findAllByComite(Comite comite);

	List<Funcionario> findAllByComite_IdGreaterThanAndComite_IdLessThanAndRegiao_IdGreaterThanAndRegiao_IdLessThanAndFuncao_IdGreaterThanAndFuncao_IdLessThanAndSkill_IdGreaterThanAndSkill_IdLessThan(
			long minComite, long maxComite, long minRegiao, long maxRegiao, long minFuncao, long maxFuncao,
			long minSkill, long maxSkill);

	List<Funcionario> findAllByOrderByNomeAsc();

	List<Funcionario> findAllByOrderByDataIntegracaoAsc();

	Comite findByComite(Long id);


	List<FuncionarioPesquisa> findAllProjectedByNomeContainingIgnoreCase(String nome);

	List<FuncionarioPesquisa> findAllProjectedByCpfContaining(String cpf);

}
