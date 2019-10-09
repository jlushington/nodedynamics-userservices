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
import com.nodedynamics.userservices.models.company.CompanyModel;
import com.nodedynamics.userservices.models.user.UserModel;
import com.nodedynamics.userservices.service.impl.CompanyServiceImpl;
import com.nodedynamics.userservices.service.impl.UserServiceImpl;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	private CompanyServiceImpl service;
	
	@Autowired
	Gson gson;
	

	@PostMapping(value = "/addcompany", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mono<ResponseModel>> AddCompany(@RequestBody String request){
		
		Mono<ResponseModel> response = service.CreateCompany(gson.fromJson(request, CompanyModel.class));
		HttpStatus status = response != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		
		return new ResponseEntity<Mono<ResponseModel>>(response, status);

	}
	

}
