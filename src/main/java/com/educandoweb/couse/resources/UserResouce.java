package com.educandoweb.couse.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.couse.entities.User;

@RestController
@RequestMapping(value = "/users")
public class UserResouce {

	@GetMapping
	public ResponseEntity<User> findAll(){
		User u = new User(1L, "paulo", "paulo@gmail.com", "11111", "123");
		return ResponseEntity.ok().body(u);
	}
	
}
