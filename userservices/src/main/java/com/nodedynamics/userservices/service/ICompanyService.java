package com.nodedynamics.userservices.service;

import com.nodedynamics.userservices.models.common.ResponseModel;
import com.nodedynamics.userservices.models.company.CompanyModel;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICompanyService {
	
	public abstract Mono<ResponseModel> CreateCompany(CompanyModel company);
	public abstract Mono<ResponseModel> UpdateCompany(CompanyModel company);
	public abstract Mono<ResponseModel> DeactivateCompany(CompanyModel company);
	public abstract Mono<ResponseModel> ActivateCompany(CompanyModel company);
	public abstract Flux<CompanyModel> ListCompany(CompanyModel company);
	

}
