package com.educandoweb.couse.repositores;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.couse.entities.Funcionario;
import com.educandoweb.couse.entities.Hierarquia;

public interface HierarquiaRepository extends JpaRepository<Hierarquia, Long>{

	Hierarquia findByFuncionarioAndRelacionamento(Long funcionario, char relacionamento);

	Hierarquia findByComiteAndRelacionamento(Long comite, char relacionamento);

	List<Hierarquia> findAllByFuncionario (Long funcionario);

	List<Hierarquia> findAllByComiteAndRelacionamento(Long comite, char relacionamento);


}
