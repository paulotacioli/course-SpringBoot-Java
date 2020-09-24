package com.educandoweb.couse.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.couse.entities.Funcao;
import com.educandoweb.couse.entities.Funcionario;
import com.educandoweb.couse.entities.Regiao;
import com.educandoweb.couse.entities.Skills;
import com.educandoweb.couse.repositores.FuncaoRepository;
import com.educandoweb.couse.repositores.FuncionarioRepository;
import com.educandoweb.couse.repositores.SkillsRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private SkillsRepository orderRepository;
	
	@Autowired
	private FuncaoRepository funcaoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		
		
		
		Funcionario f1 = new Funcionario(null, "Maria Brown", "maria@gmail.com", "988888888", "123456", "123456", "descricao", Long.parseLong("111111"), 0,0);
		Funcionario f2 = new Funcionario(null,"Paulo Tacioli", "Paulo@gmail.com", "111111", "123", "123", "descricao", Long.parseLong("222222"), 0, 0);
		
		Funcao p1 = new Funcao("The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Funcao p2 = new Funcao("Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Funcao p3 = new Funcao("Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Funcao p4 = new Funcao("PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Funcao p5 = new Funcao("Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

		
		funcaoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		

		funcaoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		Skills o1 = new Skills(null, "habilidade", f1);
		Skills o2 = new Skills(null, "habilidade", f2);
		Skills o3 = new Skills(null, "habilidade", f1);
		
		funcionarioRepository.saveAll(Arrays.asList(f1, f2));
		
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
		Regiao saoPaulo = new Regiao(null, "Sao Paulo", "São josé dos Campos", f1);
		f1.setRegiao(saoPaulo); 
		
		funcionarioRepository.save(f1);
		
	}

}
