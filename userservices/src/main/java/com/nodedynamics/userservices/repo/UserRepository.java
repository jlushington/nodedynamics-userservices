package com.nodedynamics.userservices.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.nodedynamics.userservices.models.user.UserModel;

public interface UserRepository extends MongoRepository<UserModel, String>{
	
	Optional<List<UserModel>> findByCompanyIDAndUsername(String CompanyID, String Username);

}
