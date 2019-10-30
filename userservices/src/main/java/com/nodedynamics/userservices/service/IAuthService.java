package com.nodedynamics.userservices.service;

import com.nodedynamics.userservices.models.common.ResponseModel;
import com.nodedynamics.userservices.models.user.UserModel;

import reactor.core.publisher.Mono;

public interface IAuthService {
	
	public abstract Mono<ResponseModel> Login(UserModel user);
	public abstract Mono<ResponseModel> Logout(UserModel user);
	public abstract Mono<ResponseModel> RefreshToken(String JWTToken);
	public abstract Mono<ResponseModel> InActivateToken(String JWTToken);
	
	

}
