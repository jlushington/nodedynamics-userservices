package com.nodedynamics.userservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class UserservicesApplication {
	
	@Bean
	public ValidatingMongoEventListener validatingMongoEventListener() {
	    return new ValidatingMongoEventListener(validator());
	}

	@Bean
	public LocalValidatorFactoryBean validator() {
	    return new LocalValidatorFactoryBean();
	}
	
	@Bean
	public BCryptPasswordEncoder bCrypt() {
	 return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(UserservicesApplication.class, args);
	}

}
