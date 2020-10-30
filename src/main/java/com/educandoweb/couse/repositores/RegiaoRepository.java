package com.educandoweb.couse.repositores;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.couse.entities.Regiao;

public interface RegiaoRepository extends JpaRepository<Regiao, Long>{

	List<Regiao> findAllByEstado(String estado);
}
