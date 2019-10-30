package com.nodedynamics.userservices.service.impl;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.nodedynamics.userservices.common.SecurityConstants;
import com.nodedynamics.userservices.models.common.ResponseModel;
import com.nodedynamics.userservices.models.user.UserAuthModel;
import com.nodedynamics.userservices.models.user.UserModel;
import com.nodedynamics.userservices.repo.AuthRepository;
import com.nodedynamics.userservices.service.IAuthService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import static java.util.Collections.emptyList;

import java.util.Date;

@Slf4j
@Service
public class AuthServiceImpl implements IAuthService, UserDetailsService{
	
	@Autowired
	AuthRepository repo;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("AuthServiceImpl->loadUserByUsername");
		Optional<UserModel> ouser = repo.findByUsername(username);
		
		log.info("AuthServiceImpl->loadUserByUsername->ouser.isEmpty(): "+ ouser.isEmpty());
		if(ouser.isEmpty()) {

			throw new UsernameNotFoundException(username);
		}
		UserModel user = ouser.get();
		
		log.info("AuthServiceImpl->loadUserByUsername->user: "+ user.getUsername());
		
		return new UserAuthModel(user.getId(), user.getCompanyID(), user.getUsername(), user.getPassword(), user.getIsActive());
	}


	@Override
	public Mono<ResponseModel> Login(UserModel user) {
        
		
		return null;
	}


	@Override
	public Mono<ResponseModel> Logout(UserModel user) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Mono<ResponseModel> RefreshToken(String JWTToken) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Mono<ResponseModel> InActivateToken(String JWTToken) {
		// TODO Auto-generated method stub
		return null;
	}

}
