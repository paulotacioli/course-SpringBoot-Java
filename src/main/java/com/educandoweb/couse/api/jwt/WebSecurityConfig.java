package com.educandoweb.couse.api.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.educandoweb.couse.services.LoginService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	@Autowired
	private LoginService loginService;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	// @Autowired
	// private LoginService loginService;

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		// auth//.loginService(loginService).passwordEncoder(passwordEncoder());

		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
		// auth.inMemoryAuthentication().withUser("123").password("{noop}senha").roles("ADMIN");//.and()
		// .withUser("admin").password("password").roles("USER", "ADMIN");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		System.out.println("TEMOS: 41");
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new LoginService();
	};

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		System.out.println("TEMOS: 40");
		return super.authenticationManagerBean();
	}

	@Bean
	GrantedAuthorityDefaults grantedAuthorityDefaults() {
		return new GrantedAuthorityDefaults("USER"); // Remove the ROLE_ prefix
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
// We don't need CSRF for this example
		httpSecurity.csrf().disable()
// dont authenticate this particular request
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/comites", "/funcoes", "/skills")
//				.permitAll()
//				.antMatchers(HttpMethod.POST, "/skills")
				.hasAuthority("ROLE_ADMINISTRADOR")

				.antMatchers(HttpMethod.POST, "/pendencias")
				
				.hasAnyAuthority("ROLE_COORDENADOR", "ROLE_FUNCIONARIO")
				
				.antMatchers(HttpMethod.POST, "funcionarios/pendentes/aprovar", "funcionarios/filtrar", 
						"/objetivos")
				
				.hasAnyAuthority("ROLE_ADMINISTRADOR", "ROLE_COORDENADOR")
				
				.antMatchers(HttpMethod.POST, "/pendencias/funcionario", "/authenticate")
				
				.permitAll()
//				.hasAnyAuthority("ROLE_ADMINISTRADOR", "ROLE_COORDENADOR", "ROLE_FUNCIONARIO")
				
				.antMatchers(HttpMethod.GET, "/funcionarios/ordenar-alfabetica", "/funcionarios/ordenar-integracao", 
						"regioes/cidade/{estado}", "/skills", "/acoes", "/authenticate")
				
				.permitAll()
//				.hasAnyAuthority("ROLE_ADMINISTRADOR", "ROLE_COORDENADOR", "ROLE_FUNCIONARIO")
				
				.antMatchers(HttpMethod.GET, "/funcionarios/reprovados", "/funcionarios/pendentes", "/hierarquias/encontrar-time{cpf}", 
						"/hierarquias/encontrar-lider/{cpf}")
				
				.hasAnyAuthority("ROLE_ADMINISTRADOR", "ROLE_COORDENADOR")
				
				.antMatchers(HttpMethod.PUT, "/funcionarios/atualizar-foto", "/funcionarios/atualizar-dados",
						"/funcionarios/atualizar-curriculo", "/funcionarios/atualizar-skills")
				
				.hasAuthority( "ROLE_FUNCIONARIO")
				
				.antMatchers(HttpMethod.PUT, "/comites/atualizar-foto", "/comites/atualizar-dados", "/comites")
				
				.hasAnyAuthority("ROLE_ADMINISTRADOR", "ROLE_COORDENADOR")
				
				.antMatchers(HttpMethod.DELETE, "/comites", "/funcoes", "/skills")
				
				.hasAuthority("ROLE_ADMINISTRADOR")
				
				.antMatchers(HttpMethod.DELETE, "/objetivos")
				
				.hasAnyAuthority("ROLE_ADMINISTRADOR", "ROLE_COORDENADOR")
				
				.antMatchers(HttpMethod.DELETE, "/pendencias")
				
				.hasAnyAuthority("ROLE_COORDENADOR", "ROLE_FUNCIONARIO")
				
				.and()
				


// make sure we use stateless session; session won't be used to
// store user's state.
				.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()

				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
// Add a filter to validate the tokens with every request
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
}