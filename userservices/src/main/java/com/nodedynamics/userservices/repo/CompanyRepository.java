package com.nodedynamics.userservices.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.nodedynamics.userservices.models.company.CompanyModel;

public interface CompanyRepository extends MongoRepository<CompanyModel, String>{

}
