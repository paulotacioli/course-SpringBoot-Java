package com.educandoweb.couse.repositores;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.couse.entities.Funcionario;
import com.educandoweb.couse.entities.Pendencia;
import com.educandoweb.couse.entities.Skills;

public interface SkillsRepository extends JpaRepository<Skills, Long>{

	List<Funcionario> findAllBySkillIn(List<Skills> id);

	List<Skills> findAllByFuncionario(Funcionario obj);



}
