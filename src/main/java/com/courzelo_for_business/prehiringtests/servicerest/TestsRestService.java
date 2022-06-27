package com.courzelo_for_business.prehiringtests.servicerest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import com.courzelo_for_business.prehiringtests.entities.Business;
import com.courzelo_for_business.prehiringtests.entities.Tests;
import com.courzelo_for_business.prehiringtests.entities.dtos.TestsDTO;
import com.courzelo_for_business.prehiringtests.repositories.TestsRepository;
import com.courzelo_for_business.prehiringtests.servicerest.iservicesrest.IServiceRestTests;

@Service
public class TestsRestService implements IServiceRestTests {

	
	@Autowired
    private ModelMapper mapper;
    
    @Autowired
    private  TestsRepository testRepository;
    
    
    @Autowired
    RestTemplateBuilder restTemplateBuilder;
   
 
    private static final String GET_BUSINESS_BY_ID_API = "https://springgateway.herokuapp.com/business-auth/api/auth/{userId}";
    
    
    @Override
    public List<TestsDTO> getAllTests(){
		List<Tests> tests = testRepository.findAll();
		
		tests.forEach(t->{
			if(t.getBusiness()!=null) {
				Map<String, String> params2 = new HashMap<String, String>();
				params2.put("userId", t.getBusiness().getIdBusiness());
				Business business = restTemplateBuilder.build().getForObject(GET_BUSINESS_BY_ID_API, Business.class, params2);
			    t.setBusiness(business);
			}
		});
		
		return tests.stream().map(test -> mapper.map(test, TestsDTO.class))
		.collect(Collectors.toList());
		
	}
    
    @Override
    public List<TestsDTO> getByBusiness(String idBusiness ){
		List<Tests> tests = testRepository.findByBusinessIdBusiness(idBusiness);
		
		tests.forEach(t->{
			if(t.getBusiness()!=null) {
				Map<String, String> params2 = new HashMap<String, String>();
				params2.put("userId", t.getBusiness().getIdBusiness());
				Business business = restTemplateBuilder.build().getForObject(GET_BUSINESS_BY_ID_API, Business.class, params2);
			    t.setBusiness(business);
			}
		});
	    
		return tests.stream().map(test -> mapper.map(test, TestsDTO.class))
		.collect(Collectors.toList());
		
	}
    
    
    
	public TestsDTO addTest(TestsDTO test,String idBusiness) {
		   Tests thetest = mapper.map(test, Tests.class);
		    Map<String, String> params2 = new HashMap<String, String>();
			params2.put("userId", idBusiness);
			Business business = restTemplateBuilder.build().getForObject(GET_BUSINESS_BY_ID_API, Business.class, params2);
			thetest.setBusiness(business);
			
			
		   Tests newTest = testRepository.save(thetest);
	        return mapper.map(newTest, TestsDTO.class);
		
	}
	
	
	@Override
	public TestsDTO updateTest(String idTest , TestsDTO requestTest) {
		
		Tests test = mapper.map(requestTest, Tests.class);
		
		Tests theTest = testRepository.findByIdTest(idTest);
    	theTest.setIdTest(test.getIdTest());
   
    	
    	Map<String, String> params2 = new HashMap<String, String>();
		params2.put("userId", requestTest.getBusiness().getIdBusiness());
		Business business = restTemplateBuilder.build().getForObject(GET_BUSINESS_BY_ID_API, Business.class, params2);
		theTest.setBusiness(business);
		
		
    	theTest.setIdQuiz(test.getIdQuiz());
    	theTest.setDateCreation(test.getDateCreation());
    	
     	
    	
    	Tests newTest = testRepository.save(theTest);
		
				
		return mapper.map(newTest, TestsDTO.class);
		
		
	}
	
	
	@Override
	public void deleteTest(String idTest) {
		testRepository.deleteById(idTest);
		
		
		
	}
	
	
}
