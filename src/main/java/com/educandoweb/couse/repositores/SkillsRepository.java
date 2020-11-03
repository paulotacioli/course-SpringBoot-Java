package com.educandoweb.couse.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.couse.entities.Skills;

public interface SkillsRepository extends JpaRepository<Skills, Long>{

//	List<Funcionario> findAllFuncionariosBySkill(String skill);

}
