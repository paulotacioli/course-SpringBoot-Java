package com.educandoweb.couse.repositores;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.couse.entities.Funcionario;
import com.educandoweb.couse.entities.Regiao;
import com.educandoweb.couse.entities.Skills;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

	boolean existsById(Long cpf);

	Funcionario findByCpf(Long cpf);

//	Optional<Funcionario> findByNome(String nome);

}
