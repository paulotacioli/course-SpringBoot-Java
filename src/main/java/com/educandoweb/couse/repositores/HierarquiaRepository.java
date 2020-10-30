package com.educandoweb.couse.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.couse.entities.Comite;
import com.educandoweb.couse.entities.Hierarquia;

public interface HierarquiaRepository extends JpaRepository<Hierarquia, Long>{

	static Hierarquia findByFuncionarioAndRelacionamento(Long cpf, char s) {
		return null;
	}

	static Hierarquia findByComiteAndRelacionamento(Long comite, char c) {
		return null;
	}
}
