package com.nodedynamics.userservices.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.nodedynamics.userservices.models.common.ResponseModel;
import com.nodedynamics.userservices.models.user.UserModel;
import com.nodedynamics.userservices.service.impl.UserServiceImpl;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {
	
	
	@Autowired
	private UserServiceImpl service;
	
	@Autowired
	Gson gson;
	
	@PostMapping(value = "/adduser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mono<ResponseModel>> AddUser(@RequestBody String request){
		
		Mono<ResponseModel> response = service.CreateUser(gson.fromJson(request, UserModel.class));
		HttpStatus status = response != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		
		return new ResponseEntity<Mono<ResponseModel>>(response, status);
	}
	
	@PostMapping(value = "/updateuser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mono<ResponseModel>> UpdateUser(@RequestBody String request){
		
		Mono<ResponseModel> response = service.UpdateUser(gson.fromJson(request, UserModel.class));
		HttpStatus status = response != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		
		return new ResponseEntity<Mono<ResponseModel>>(response, status);
	}
	
	@PostMapping(value = "/deactivateuser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mono<ResponseModel>> DeactivateUser(@RequestBody String request){
		
		Mono<ResponseModel> response = service.DeactiveteUser(gson.fromJson(request, UserModel.class));
		HttpStatus status = response != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		
		return new ResponseEntity<Mono<ResponseModel>>(response, status);
	}
	
	@PostMapping(value = "/activateuser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mono<ResponseModel>> ActivateUser(@RequestBody String request){
		
		Mono<ResponseModel> response = service.ActivateUser(gson.fromJson(request, UserModel.class));
		HttpStatus status = response != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		
		return new ResponseEntity<Mono<ResponseModel>>(response, status);
	}
	
	@PostMapping(value = "/listusers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Flux<UserModel>> ListUser(@RequestBody String request){
		
		Flux<UserModel> response = service.ListUsers(gson.fromJson(request, UserModel.class));
		
		HttpStatus status = response != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		
		return new ResponseEntity<Flux<UserModel>>(response, status);
		
		
	}
	


}
