package com.educandoweb.couse.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.couse.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

}
