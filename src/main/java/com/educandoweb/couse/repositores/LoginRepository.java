package com.educandoweb.couse.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.couse.entities.Login;

public interface LoginRepository extends JpaRepository<Login, Long>{

}
