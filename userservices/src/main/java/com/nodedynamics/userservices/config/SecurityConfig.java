package com.nodedynamics.userservices.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//import com.auth0.spring.security.api.JwtAuthenticationEntryPoint;
import com.nodedynamics.userservices.repo.AuthRepository;
import com.nodedynamics.userservices.service.impl.AuthServiceImpl;

import lombok.extern.slf4j.Slf4j;

@EnableWebSecurity
@EnableMongoRepositories(basePackageClasses=AuthRepository.class)
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private AuthServiceImpl userDetailsService;
	

@Autowired
private JwtRequestFilter jwtRequestFilter;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(getPasswordEncoder());
	}
	

@Autowired
public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
// configure AuthenticationManager so that it knows from where to load
// user for matching credentials
// Use BCryptPasswordEncoder
	auth.userDetailsService(userDetailsService)
	.passwordEncoder(getPasswordEncoder());
}
	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		/*
		log.info("SecurityConfig->configure");
		http.csrf().disable();
		http.authorizeRequests()
		.antMatchers("/api/auth/secured/all").authenticated()
		.anyRequest().permitAll()
		.and().formLogin().permitAll();
		*/
		
		http.csrf().disable()
		.authorizeRequests().antMatchers("/api/auth/authenticate").permitAll()
		.anyRequest().authenticated().and()
		.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		

		// Add a filter to validate the tokens with every request
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	
	//Need to change the password encoder to use bcrypt
	private PasswordEncoder getPasswordEncoder() {
		return new PasswordEncoder() {

			@Override
			public String encode(CharSequence rawPassword) {
				log.info("SecurityConfig->getPasswordEncoder()->encode: "+ rawPassword);
				return rawPassword.toString();
			}

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return BCrypt.checkpw((String) rawPassword, encodedPassword);
				//return true;
				
			}
		};
	}

	
}
