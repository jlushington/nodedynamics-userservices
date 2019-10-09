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
		repos= super.getRepo();
		UserModel um = (UserModel)super.getModel();
		
		log.info("OrgExist->OrgExistExecute");
		
		log.info("OrgExist->OrgExistExecute->repos: " + repos.hashCode());
		//UserRepository repo= (UserRepository)repos.get(EValidation.Repos.USER);
		//log.info("OrgExist->OrgExistExecute->existsById"+ repo.existsById("5d994870110e8d7caff4428d"));
		//CompanyRepository repo = (CompanyRepository)super.getRepo().get(EValidation.Repos.COMPANY);
		
		//UserModel um = (UserModel)super.getModel();
		//log.info("OrgExist->OrgExistExecute-> um.getCompanyID() : " +  um.getCompanyID());
		
		CompanyRepository repo= (CompanyRepository)repos.get(EValidation.Repos.COMPANY);
		

		
		if(!(repo.existsById(um.getCompanyID()))) {
			super.setIsValid(false);
		}else {
			super.setIsValid(true);
		}
		
	}

	@Override
	public void setModel(CoreModel m) {
		super.setModel(m);		
	}
	
	@Override
	public void setRepo(HashMap<Repos, MongoRepository> r) {
		log.info("OrgExist->setRepo-> r.hashCode(): "+ r.hashCode());
		super.setRepo(r);
	}	

}
