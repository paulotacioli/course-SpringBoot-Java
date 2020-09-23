package com.educandoweb.couse.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.couse.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
}
