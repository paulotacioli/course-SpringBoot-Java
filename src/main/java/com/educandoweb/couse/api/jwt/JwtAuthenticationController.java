package com.educandoweb.couse.api.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.couse.entities.Funcionario;
import com.educandoweb.couse.entities.Login;
import com.educandoweb.couse.services.FuncionarioService;
import com.educandoweb.couse.services.LoginService;
import com.educandoweb.couse.services.exceptions.ErroNaoMapeadoException;
import com.educandoweb.couse.services.exceptions.FuncionarioNegadoException;
import com.educandoweb.couse.services.exceptions.FuncionarioPendenteAprovacaoException;
import com.educandoweb.couse.services.exceptions.UsuarioInvalidoException;

@RestController
@CrossOrigin
@EnableWebSecurity
public class JwtAuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private LoginService loginService;
	@Autowired
	private FuncionarioService funcionarioService;
	
	private Funcionario funcionario;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		System.out.println("Aqui entrou no public responseEntity /authenticate");
		funcionario = new Funcionario ();
		
		funcionario = funcionarioService.findById(authenticationRequest.getCpf());
		
		if (funcionario.getAprovado() == 0) {
			System.out.println("Aqui entrou no funcionario getAprovado = 0");
			throw new FuncionarioPendenteAprovacaoException("Pendente aprovação.");
		} else if (funcionario.getAprovado() != 1 && funcionario.getAprovado() != 2 && funcionario.getAprovado() != 10) {
			System.out.println("Aqui entrou no funcionario getAprovado = 1");
			throw new FuncionarioNegadoException ("Não aprovado.");
		} 
		authenticate1(authenticationRequest.getCpf(), authenticationRequest.getSenha());
		final Login login = loginService.findByCpf(authenticationRequest.getCpf());
		final String token = jwtTokenUtil.generateToken(login);
		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate1(Long cpf, String senha) throws Exception {
		System.out.println("Aqui entrou authenticate1 (long cpf, string senha)");
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(cpf, senha));

		} catch (DisabledException e) {

			throw new Exception("USER_DISABLED", e);
			
		} catch (BadCredentialsException e) {
			throw new UsuarioInvalidoException("");
		} catch (RuntimeException e) {
			throw new ErroNaoMapeadoException ("");
		}
	}
}