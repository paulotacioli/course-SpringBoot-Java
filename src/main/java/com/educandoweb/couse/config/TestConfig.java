package com.educandoweb.couse.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.couse.entities.Category;
import com.educandoweb.couse.entities.Skills;
import com.educandoweb.couse.entities.User;
import com.educandoweb.couse.repositores.CategoryRepository;
import com.educandoweb.couse.repositores.SkillsRepository;
import com.educandoweb.couse.repositores.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SkillsRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456", "123456", "descricao", Long.parseLong("111111"), 0,0);
		User u2 = new User(null,"Paulo Tacioli", "Paulo@gmail.com", "111111", "123", "123", "descricao", Long.parseLong("222222"), 0, 0);
		
		Skills o1 = new Skills(null, "habilidade", u1);
		Skills o2 = new Skills(null, "habilidade", u2);
		Skills o3 = new Skills(null, "habilidade", u1);
		
		userRepository.saveAll(Arrays.asList(u1, u2));
		
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
	}

}
