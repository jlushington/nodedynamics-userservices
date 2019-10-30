package com.nodedynamics.userservices.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nodedynamics.userservices.models.user.UserModel;

public interface AuthRepository extends MongoRepository<UserModel, String>{
	
	Optional<UserModel> findByUsername(String Username);

}
