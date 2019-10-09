package com.nodedynamics.userservices.validate;

import java.util.HashMap;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.nodedynamics.userservices.common.EValidation;
import com.nodedynamics.userservices.models.CoreModel;


public interface Validate {
	
	void setModel(CoreModel model);
	CoreModel getModel();
	Boolean getIsValid();
	void setIsValid(Boolean valid);
	HashMap<EValidation.Repos, MongoRepository> getRepo();
	void setRepo(HashMap<EValidation.Repos, MongoRepository>  r);
	
	void validateExecute();

}
