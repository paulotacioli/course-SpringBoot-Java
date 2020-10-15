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
				.antMatchers(HttpMethod.POST, "/comites", "/funcionarios", "/authenticate", 
						"/pendencias", "/regioes", "/funcoes")
				.permitAll()
				.antMatchers(HttpMethod.POST, "/skills")
				.hasAuthority("ROLE_ADMINISTRADOR")
				.and()
//				.antMatchers(HttpMethod.GET, "/comites", "/funcionarios", "/authenticate", 
//						"/pendencias", "/regioes", "/skills", "/funcoes")
//				.permitAll()
//
//				.antMatchers(HttpMethod.PUT, "/comites", "/funcionarios", "/authenticate", 
//						"/pendencias", "/regioes", "/skills", "/funcoes")
//				.permitAll().and()

				/*
				 * .antMatchers(HttpMethod.POST, "/authenticate","/corretores",
				 * "/anuncios/filtrar", "/diretorios").permitAll() .antMatchers(HttpMethod.GET,
				 * "/anuncios", "/diretorios").permitAll()
				 * .antMatchers(HttpMethod.POST,"/proprietarios", "/anuncios",
				 * "/localizacao").authenticated() .antMatchers(HttpMethod.POST,
				 * "/*").hasAuthority("ROLE_ADMIN").and() .antMatchers(HttpMethod.POST,
				 * "/motorista", "/transportadora").permitAll()
				 */

// make sure we use stateless session; session won't be used to
// store user's state.
				.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()

				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
// Add a filter to validate the tokens with every request
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
}