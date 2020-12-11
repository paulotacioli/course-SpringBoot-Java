package com.educandoweb.couse.repositores;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.couse.entities.Funcionario;
import com.educandoweb.couse.entities.Pendencia;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

	boolean existsById(Long cpf);

	Funcionario findByCpf(Long cpf); 

	List<Pendencia> findAllByCpf(Long cpf);

	List<Funcionario> findAllByAprovado(int aprovado);

}
