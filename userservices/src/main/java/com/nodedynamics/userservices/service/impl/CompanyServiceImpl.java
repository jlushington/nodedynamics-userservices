package com.nodedynamics.userservices.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nodedynamics.userservices.models.common.ResponseModel;
import com.nodedynamics.userservices.models.company.CompanyModel;
import com.nodedynamics.userservices.repo.CompanyRepository;
import com.nodedynamics.userservices.service.ICompanyService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CompanyServiceImpl implements ICompanyService{
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	CompanyRepository repo;


	@Override
	public Mono<ResponseModel> CreateCompany(CompanyModel company) {
		
		repo.save(company);
		
		ResponseModel model =ResponseModel.builder().MessageTypeID(0).MessageType("Successful").Message("Test001").build();
	
		return Mono.just(model);
		
	}


	@Override
	public Mono<ResponseModel> UpdateCompany(CompanyModel company) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Mono<ResponseModel> DeactivateCompany(CompanyModel company) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Mono<ResponseModel> ActivateCompany(CompanyModel company) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Flux<CompanyModel> ListCompany(CompanyModel company) {
		// TODO Auto-generated method stub
		return null;
	}
	


}
