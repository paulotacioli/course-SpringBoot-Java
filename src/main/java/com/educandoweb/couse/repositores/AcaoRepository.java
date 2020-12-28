package com.educandoweb.couse.repositores;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.couse.entities.Acao;
import com.educandoweb.couse.entities.Objetivo;

public interface AcaoRepository extends JpaRepository<Acao, Long>{
	
	List<Acao> findAllByObjetivo (Objetivo id);

	List<Acao> findAllByObjetivo_Id(Long id);
}


