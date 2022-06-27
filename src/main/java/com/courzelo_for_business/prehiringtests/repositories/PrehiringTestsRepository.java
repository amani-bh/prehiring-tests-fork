package com.courzelo_for_business.prehiringtests.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.courzelo_for_business.prehiringtests.entities.PrehiringTests;

@Repository
public interface PrehiringTestsRepository extends MongoRepository<PrehiringTests,String>{
	
		public PrehiringTests findByTitle(String title);
		public PrehiringTests findByIdPrehiringTest(String idPrehiringTest);
		public List<PrehiringTests> findByBusinessIdBusiness(String idBusiness);

}
