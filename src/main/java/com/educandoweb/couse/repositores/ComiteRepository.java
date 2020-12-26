package com.educandoweb.couse.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.couse.entities.Comite;

public interface ComiteRepository extends JpaRepository<Comite, Long>{

	Comite findById(long id);


}
