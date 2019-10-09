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

import com.nodedynamics.userservices.common.EValidation;
import com.nodedynamics.userservices.common.EValidation.Repos;
import com.nodedynamics.userservices.models.common.ResponseModel;
import com.nodedynamics.userservices.models.user.UserModel;
import com.nodedynamics.userservices.repo.CompanyRepository;
import com.nodedynamics.userservices.repo.UserRepository;
import com.nodedynamics.userservices.service.IUserService;
import com.nodedynamics.userservices.validate.Validate;
import com.nodedynamics.userservices.validate.ValidationBuilder;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements IUserService{
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	CompanyRepository CompanyRepo;
	

	private HashMap<Repos, MongoRepository> repos= new HashMap<Repos, MongoRepository>(); 
	

	@Override
	public Mono<ResponseModel> CreateUser(UserModel user) {
		//RETURN MESAGE;
		ResponseModel model=null;
		repos.put(EValidation.Repos.USER, userRepo);
		repos.put(EValidation.Repos.COMPANY, CompanyRepo);
		
		log.info("UserServiceImpl->CreateUser->repos.get(EValidation.Repos.USER).hashCode() : " + repos.get(EValidation.Repos.USER).hashCode());
		log.info("UserServiceImpl->CreateUser->repos.get(EValidation.Repos.COMPANY).hashCode() : " + repos.get(EValidation.Repos.COMPANY).hashCode());

		
		Validate val2 = new ValidationBuilder().UserValidate().OrgValidate().build();
		val2.setModel(user);
		val2.setRepo(repos);
		val2.validateExecute();
		
		
		//BUSINESS LOGIC - CHECK IF COMPANY ID AND USERNAME ALREADY EXIST
		Optional<List<UserModel>> CheckExt=userRepo.findByCompanyIDAndUsername(user.getCompanyID(), user.getUsername());
		if(!(CheckExt.get().isEmpty())) {
			model =ResponseModel.builder().MessageTypeID(0).MessageType("ERROR").Message("Company Already Exist").build();
			
		}else {		
			UserModel um = UserModel.builder()
					.companyID(user.getCompanyID())
					.username(user.getUsername())
					.password( BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()) )
					.build();
			
			userRepo.save(um);
			
			model =ResponseModel.builder().MessageTypeID(0).MessageType("Successful").Message("User Successfully Created").build();
		}
	
		return Mono.just(model);
	}

	@Override
	public Mono<ResponseModel> UpdateUser(UserModel user) {
		// TODO Auto-generated method stub
		return null;
	}

}
