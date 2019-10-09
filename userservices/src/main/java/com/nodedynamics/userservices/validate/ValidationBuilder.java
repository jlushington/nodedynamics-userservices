package com.nodedynamics.userservices.validate;

import com.nodedynamics.userservices.validate.impl.ValidateImpl;
import com.nodedynamics.userservices.validate.impl.validateuser.OrgExist;
import com.nodedynamics.userservices.validate.impl.validateuser.UserExist;

public class ValidationBuilder {
	
	private Validate validationService = new ValidateImpl();
	
	
	
	public ValidationBuilder UserValidate() {
		validationService = new UserExist(validationService);
		return this;
	}
	
	public ValidationBuilder OrgValidate() {
		validationService = new OrgExist(validationService);
		return this;
	}
	
	public Validate build() {
		Validate v = validationService;
		validationService = new ValidateImpl();
		return v;
		
	}

}
