package com.educandoweb.couse.repositores;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.couse.entities.Objetivo;

public interface ObjetivoRepository extends JpaRepository<Objetivo, Long>{

	List<Objetivo> findAllByComite_Id(Long id);
	
}