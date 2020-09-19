package com.educandoweb.couse.config;

import java.sql.Date;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.couse.entities.User;
import com.educandoweb.couse.repositores.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456", "123456", "descricao", Long.parseLong("111111"), 0,0);
		User u2 = new User(null,"Paulo Tacioli", "Paulo@gmail.com", "111111", "123", "123", "descricao", Long.parseLong("222222"), 0, 0);
		
		userRepository.saveAll(Arrays.asList(u1, u2));
		
	}

}
