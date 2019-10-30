package com.nodedynamics.userservices.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.nodedynamics.userservices.models.common.ResponseModel;
import com.nodedynamics.userservices.models.user.UserModel;
import com.nodedynamics.userservices.service.impl.UserServiceImpl;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AccessController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	
	@Autowired
	private UserServiceImpl service;
	
	@Autowired
	Gson gson;
	

	@GetMapping("/all")
    public String hello() {
        return "Hello Youtube";
    }

    @GetMapping("/secured/all")
    public String securedHello() {
        return "Secured Hello";
    }

    @GetMapping("/secured/alternate")
    public String alternate() {
        return "alternate";
    }
    
    @PostMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserModel authenticationRequest) throws Exception {
    	log.info("AccessController->authenticate");
    	
    	try {
    		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
    		
    		
    		} catch (DisabledException e) {
    		throw new Exception("USER_DISABLED", e);
    		} catch (BadCredentialsException e) {
    		throw new Exception("INVALID_CREDENTIALS", e);
    		}
    		
    	

    	return ResponseEntity.ok("sometrhing");
    }

    
	/*
	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mono<ResponseModel>> Login(@RequestBody String request){
		
		//Mono<ResponseModel> response = service.CreateUser(gson.fromJson(request, UserModel.class));
		//HttpStatus status = response != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		
		//return new ResponseEntity<Mono<ResponseModel>>(response, status);
		return null;
	}
	*/

}
