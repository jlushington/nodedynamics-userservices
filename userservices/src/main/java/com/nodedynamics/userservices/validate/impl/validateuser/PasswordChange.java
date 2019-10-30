package com.nodedynamics.userservices.validate.impl.validateuser;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.nodedynamics.userservices.common.EValidation;
import com.nodedynamics.userservices.common.EValidation.Repos;
import com.nodedynamics.userservices.models.CoreModel;
import com.nodedynamics.userservices.models.user.UserModel;
import com.nodedynamics.userservices.repo.UserRepository;
import com.nodedynamics.userservices.validate.Validate;
import com.nodedynamics.userservices.validate.Validator;

public class PasswordChange extends Validator{
	
private final Logger log = LoggerFactory.getLogger(this.getClass());
	

	private HashMap<Repos, MongoRepository> repos;
	
	private UserModel model;
	

	public PasswordChange(Validate v) {
		super(v);
	}
	
	@Override
	public void validateExecute() {
		super.validateExecute();
		PasswordChangeValidation();
	}
	
	private void PasswordChangeValidation() {
		if(super.getIsValid()) {
			model= (UserModel)super.getModel();
			
			repos= super.getRepo();
			UserRepository repo= (UserRepository)repos.get(EValidation.Repos.USER);
			
			Optional<UserModel> rtnUserMode =repo.findById(model.getId());
			
			if(!(rtnUserMode.isEmpty())) {
				super.setIsValid(false);
			}else {
				// Static getInstance method is called with hashing SHA  
		        try {
					MessageDigest md = MessageDigest.getInstance("SHA-256");
					UserModel UM=rtnUserMode.get();
					if(toHexString(UM.getPassword().getBytes(StandardCharsets.UTF_8)).equals(toHexString(model.getPassword().getBytes(StandardCharsets.UTF_8)))) {
						super.setIsValid(true);
						
					}else {
						super.setIsValid(false);
					}
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				
				
				
				
			
				//super.setIsValid(true);
			}
			
		}
		
	}
	
	private static String toHexString(byte[] hash) {
		
        // Convert byte array into signum representation  
        BigInteger number = new BigInteger(1, hash); 
        
        // Convert message digest into hex value  
        StringBuilder hexString = new StringBuilder(number.toString(16));
        
        // Pad with leading zeros 
        while (hexString.length() < 32)  
        {  
            hexString.insert(0, '0');  
        }  
  
        return hexString.toString();  
		
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
