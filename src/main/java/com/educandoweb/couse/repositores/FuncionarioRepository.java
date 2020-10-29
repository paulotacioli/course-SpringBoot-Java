package com.educandoweb.couse.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.couse.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

	boolean existsById(Long cpf);

	static Funcionario findById(Funcionario funcionario) {
		// TODO Auto-generated method stub
		return null;
	}


}
