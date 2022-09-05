package com.courzelo_for_business.prehiringtests.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.courzelo_for_business.prehiringtests.entities.OtherTest;

@Repository
public interface OtherTestRepository extends MongoRepository<OtherTest,String>{
	
	public OtherTest findByTitle(String title);
	public OtherTest findByIdOtherTest(String idOtherTest);
	public List<OtherTest> findByBusinessIdBusiness(String idBusiness);
	public List<OtherTest> findByBusinessCompanyName(String companyName);

}