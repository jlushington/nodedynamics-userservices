package com.nodedynamics.userservices.validate.impl;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import com.nodedynamics.userservices.common.EValidation.Repos;
import com.nodedynamics.userservices.models.CoreModel;
import com.nodedynamics.userservices.validate.Validate;
@Component
public class ValidateImpl implements Validate{
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private CoreModel model;
	private Boolean isValid;
	private HashMap<Repos, MongoRepository> repos;

	@Override
	public void setModel(CoreModel m) {
		model=m;
		
	}
	@Override
	public CoreModel getModel() {
		return model;
	}
	@Override
	public void validateExecute() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Boolean getIsValid() {
		return isValid;
	}
	@Override
	public void setIsValid(Boolean valid) {
		isValid=valid;
		
	}
	@Override
	public HashMap<Repos, MongoRepository> getRepo() {
		// TODO Auto-generated method stub
		return repos;
	}
	@Override
	public void setRepo(HashMap<Repos, MongoRepository> repo) {
		repos.putAll(repo);
		log.info("ValidateImpl->setRepo");
	}	

}
