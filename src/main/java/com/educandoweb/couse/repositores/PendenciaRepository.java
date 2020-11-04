package com.educandoweb.couse.repositores;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.couse.entities.Funcionario;
import com.educandoweb.couse.entities.Pendencia;

public interface PendenciaRepository extends JpaRepository<Pendencia, Long>{

	List<Pendencia> findAllByFuncionario(Funcionario obj);

}
