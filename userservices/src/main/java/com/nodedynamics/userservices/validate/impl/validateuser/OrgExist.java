package com.nodedynamics.userservices.validate.impl.validateuser;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.nodedynamics.userservices.common.EValidation;
import com.nodedynamics.userservices.common.EValidation.Repos;
import com.nodedynamics.userservices.models.CoreModel;
import com.nodedynamics.userservices.models.user.UserModel;
import com.nodedynamics.userservices.repo.CompanyRepository;
import com.nodedynamics.userservices.repo.UserRepository;
import com.nodedynamics.userservices.validate.Validate;
import com.nodedynamics.userservices.validate.Validator;

public class OrgExist extends Validator{
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private HashMap<Repos, MongoRepository> repos;
	
	private UserModel model;
	
	
	public OrgExist(Validate validate) {
		super(validate);
	}
	
	@Override
	public void validateExecute() {
		super.validateExecute();
		OrgExistExecute();
		
	}
	
	private void OrgExistExecute() {
		if(super.getIsValid()) {
			repos= super.getRepo();
			UserModel um = (UserModel)super.getModel();
			
			CompanyRepository repo= (CompanyRepository)repos.get(EValidation.Repos.COMPANY);
			
			if(!(repo.existsById(um.getCompanyID()))) {
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

}
