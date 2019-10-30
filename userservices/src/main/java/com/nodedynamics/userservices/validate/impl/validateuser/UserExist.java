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
		
		if(super.getIsValid()) {
		
			model= (UserModel)super.getModel();
			
			repos= super.getRepo();
			UserRepository repo= (UserRepository)repos.get(EValidation.Repos.USER);
			
			Optional<List<UserModel>> CheckExt=repo.findByCompanyIDAndUsername(model.getCompanyID(), model.getUsername());
			if(!(CheckExt.get().isEmpty())) {
				super.setIsValid(false);
			}else {
				super.setIsValid(true);
			}
		}
		
	}

	@Override
	public void setModel(CoreModel m) {
		super.setModel(m);
	}
	

	@Override
	public void setRepo(HashMap<Repos, MongoRepository> r) {
		super.setRepo(r);
	}

	@Override
	public void close() {
		super.close();
		
	}	

}
