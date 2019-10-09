package com.nodedynamics.userservices.service;

import com.nodedynamics.userservices.models.common.ResponseModel;
import com.nodedynamics.userservices.models.user.UserModel;

import reactor.core.publisher.Mono;

public interface IUserService {
	
	public abstract Mono<ResponseModel> CreateUser(UserModel user);
	public abstract Mono<ResponseModel> UpdateUser(UserModel user);

}
