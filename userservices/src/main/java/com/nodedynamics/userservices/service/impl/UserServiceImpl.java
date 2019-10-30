package com.nodedynamics.userservices.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.nodedynamics.userservices.api.AccessController;
import com.nodedynamics.userservices.common.EValidation;
import com.nodedynamics.userservices.common.EValidation.Repos;
import com.nodedynamics.userservices.models.common.ResponseModel;
import com.nodedynamics.userservices.models.user.UserModel;
import com.nodedynamics.userservices.repo.CompanyRepository;
import com.nodedynamics.userservices.repo.UserRepository;
import com.nodedynamics.userservices.service.IUserService;
import com.nodedynamics.userservices.validate.Validate;
import com.nodedynamics.userservices.validate.ValidationBuilder;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class UserServiceImpl implements IUserService{

	
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	CompanyRepository CompanyRepo;
	

	private HashMap<Repos, MongoRepository> repos= new HashMap<Repos, MongoRepository>(); 
	

	@Override
	public Mono<ResponseModel> CreateUser(UserModel user) {
		//RETURN MESAGE;
		ResponseModel model=null;
		
		//ADD Repos required for Validation
		repos.put(EValidation.Repos.USER, userRepo);
		repos.put(EValidation.Repos.COMPANY, CompanyRepo);
		

		//Run Validation
		Validate val2 = new ValidationBuilder().UserValidate().OrgValidate().build();
		val2.setModel(user);
		val2.setRepo(repos);
		val2.validateExecute();
		
		
		if(!(val2.getIsValid())) {
			model =ResponseModel.builder().MessageTypeID(0).MessageType("ERROR").Message("Did Not Pass Validation").build();
			
		}else {		
			UserModel um = UserModel.builder()
					.companyID(user.getCompanyID())
					.username(user.getUsername())
					.password( BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()) )
					.build();
			
			userRepo.save(um);
			
			model =ResponseModel.builder().MessageTypeID(0).MessageType("Successful").Message("User Successfully Created").build();
		}
		val2.close();
	
		return Mono.just(model);
	}

	@Override
	public Mono<ResponseModel> UpdateUser(UserModel user) {
		//RETURN MESAGE;
		ResponseModel model=null;
		
		//TODO: Validation: check if company and user exist
		
		//TODO: Check if password has been changed and if change log entry. 
		//TODO - FUTURE CHANGE: if change by root parent log entry. 
		//TODO - FUTURE CHANGE: if changed by current user follow update process
		
		//TODO - FUTURE: IF any fields are missing use previous setting
		
				
		//if (valid){
		userRepo.save(user);
		model =ResponseModel.builder().MessageTypeID(0).MessageType("Successful").Message("User Successfully Created").build();
		
		//}else{
		//}
		
		return Mono.just(model);
	}

	@Override
	public Mono<ResponseModel> DeactiveteUser(UserModel user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<ResponseModel> ActivateUser(UserModel user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<UserModel> ListUsers(UserModel user) {
		// TODO Auto-generated method stub
		return null;
	}

}
