package com.nodedynamics.userservices.validate;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.nodedynamics.userservices.common.EValidation.Repos;
import com.nodedynamics.userservices.models.CoreModel;


public abstract class Validator implements Validate{
	

	
	private Validate val;
	private static CoreModel model=null;
	private Boolean isValid=null;
	private static HashMap<Repos, MongoRepository> repos=null;
	
	public Validator(Validate v) {
		val=v;
	}
	
	@Override
	public void validateExecute() {
		val.validateExecute();
	}
	
	@Override
	public CoreModel getModel() {
		return model;
	}
	
	@Override
	public void setModel(CoreModel m) {
		if(model==null) {
			model=m;
		}
	}
	
	@Override
	public Boolean getIsValid() {
		return isValid;
	}
	
	@Override
	public void setIsValid(Boolean valid) {
		if(isValid == null) {
			if(valid==null) {
				isValid=false;
			}
		}else{
			if(isValid == true) {
				if(valid==null) {
					isValid=false;
				}else if(valid==false) {
					isValid=false;
				}	
			}
		}
	}
	
	@Override
	public HashMap<Repos, MongoRepository> getRepo() {
		return repos;
	}
	@Override
	public void setRepo(HashMap<Repos, MongoRepository> r) {		
		if(repos==null) {
			repos=r;
		}
		
	}	
	

}
