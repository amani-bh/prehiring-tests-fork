package com.courzelo_for_business.prehiringtests.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.courzelo_for_business.prehiringtests.entities.Tests;



@Repository
public interface TestsRepository extends MongoRepository<Tests,String> {
	
	public Tests findByIdTest(String idTest);
	public List<Tests> findByBusinessIdBusiness(String idBusiness);
}
