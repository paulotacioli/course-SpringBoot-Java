package com.educandoweb.couse.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.couse.entities.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long>{
	
}