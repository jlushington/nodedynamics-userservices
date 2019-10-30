package com.nodedynamics.userservices.service;

import com.nodedynamics.userservices.models.common.ResponseModel;
import com.nodedynamics.userservices.models.user.UserModel;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserService {
	
	public abstract Mono<ResponseModel> CreateUser(UserModel user);
	public abstract Mono<ResponseModel> UpdateUser(UserModel user);
	public abstract Mono<ResponseModel> DeactiveteUser(UserModel user);
	public abstract Mono<ResponseModel> ActivateUser(UserModel user);
	public abstract Flux<UserModel> ListUsers(UserModel user);

}
