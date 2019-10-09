package com.nodedynamics.userservices.validate.impl.validateuser;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.nodedynamics.userservices.common.EValidation;
import com.nodedynamics.userservices.common.EValidation.Repos;
import com.nodedynamics.userservices.models.CoreModel;
import com.nodedynamics.userservices.models.user.UserModel;
import com.nodedynamics.userservices.repo.UserRepository;
import com.nodedynamics.userservices.validate.Validate;
import com.nodedynamics.userservices.validate.Validator;


@Service
public class UserExist extends Validator{
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	

	private HashMap<Repos, MongoRepository> repos;
	
	private UserModel model;
	
	
	public UserExist(Validate validate) {
		super(validate);
	}
	
	@Override
	public void validateExecute() {
		super.validateExecute();
		UserExistExecute();
		
	}
	
	private void UserExistExecute() {
		model= (UserModel)super.getModel();
		repos= super.getRepo();
		
		//log.info("UserExist->UserExistExecute->repos.hashCode(): " + repos.hashCode());
		//log.info("UserExist->UserExistExecute->super.getRepo().hashCode() : " + super.getRepo().size());
		//UserRepository repo= (UserRepository)repos.get(EValidation.Repos.USER);
		//log.info("UserExist->UserExistExecute->existsById"+ repo.existsById("5d994870110e8d7caff4428d"));
		
		//UserRepository repo = (UserRepository)super.getRepo().get(EValidation.Repos.USER);
		
		log.info("UserExist->UserExistExecute->getUsername: "+ model.getUsername());
		
		//BUSINESS LOGIC - CHECK IF COMPANY ID AND USERNAME ALREADY EXIST
		//UserRepository repo = new UserRepository();
		
		/*
		UserRepository repo= (UserRepository)repos.get(EValidation.Repos.USER);
		Optional<List<UserModel>> CheckExt=repo.findByCompanyIDAndUsername(model.getCompanyID(), model.getUsername());
		
		if(!(CheckExt.get().isEmpty())) {
			super.setIsValid(false);
		}else {
			super.setIsValid(true);
		}
		*/
		
	}

	@Override
	public void setModel(CoreModel m) {
		super.setModel(m);
	}
	

	@Override
	public void setRepo(HashMap<Repos, MongoRepository> r) {
		log.info("UserExist->setRepo");
		super.setRepo(r);
	}	

}
