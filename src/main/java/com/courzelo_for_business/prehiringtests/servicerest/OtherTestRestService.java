package com.courzelo_for_business.prehiringtests.servicerest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.courzelo_for_business.prehiringtests.entities.Business;
import com.courzelo_for_business.prehiringtests.entities.OtherTest;
import com.courzelo_for_business.prehiringtests.entities.PrehiringTests;
import com.courzelo_for_business.prehiringtests.entities.QuestionsOtherTest;
import com.courzelo_for_business.prehiringtests.entities.dtos.OtherTestDTO;
import com.courzelo_for_business.prehiringtests.entities.dtos.PrehiringTestsDTO;
import com.courzelo_for_business.prehiringtests.entities.dtos.QuestionsOtherTestDTO;
import com.courzelo_for_business.prehiringtests.repositories.OtherTestRepository;
import com.courzelo_for_business.prehiringtests.servicerest.iservicesrest.IServiceRestOtherTest;

@Service
public class OtherTestRestService implements IServiceRestOtherTest {
	
	  @Autowired
	    private ModelMapper mapper;
	    
	    @Autowired
	    private  SequenceGeneratorService sequenceGeneratorService;
	   
	    @Autowired
	    private  OtherTestRepository testRepository;
	    
	    @Autowired 
	    private MongoTemplate mongoTemplate;

	    
	    @Autowired
	    RestTemplateBuilder restTemplateBuilder;

//	    private static final String GET_BUSINESS_BY_ID_API = "https://springgateway.herokuapp.com/business-auth/api/auth/{userId}";
	    private static final String GET_BUSINESS_BY_ID_API = "http://localhost:8090/api/auth/{userId}";

	@Override
	public List<OtherTestDTO> getAllTests() {
		List<OtherTest> otherTests = testRepository.findAll();
		otherTests.forEach(t->{
			if(t.getBusiness()!=null) {
				Map<String, String> params2 = new HashMap<String, String>();
				params2.put("userId", t.getBusiness().getIdBusiness());
				Business business = restTemplateBuilder.build().getForObject(GET_BUSINESS_BY_ID_API, Business.class, params2);
			    t.setBusiness(business);
			}
		});
		return otherTests.stream().map(test -> mapper.map(test, OtherTestDTO.class))
		.collect(Collectors.toList());
	}

	@Override
	public List<OtherTestDTO> getTestByBusiness(String idBusiness) {
		List<OtherTest> otherTests = testRepository.findByBusinessIdBusiness(idBusiness);
    	
		otherTests.forEach(t->{
    		if(t.getBusiness()!=null) {
				Map<String, String> params2 = new HashMap<String, String>();
				params2.put("userId", t.getBusiness().getIdBusiness());
				Business business = restTemplateBuilder.build().getForObject(GET_BUSINESS_BY_ID_API, Business.class, params2);
			    t.setBusiness(business);
			}
		});
    	
    	
		return otherTests.stream().map(test -> mapper.map(test, OtherTestDTO.class))
		.collect(Collectors.toList());
	}

	@Override
	public List<OtherTestDTO> getTestByBusinessCompanyName(String companyName) {
		List<OtherTest> otherTests = testRepository.findByBusinessCompanyName(companyName);
    	
		otherTests.forEach(t->{
    		if(t.getBusiness()!=null) {
				Map<String, String> params2 = new HashMap<String, String>();
				params2.put("userId", t.getBusiness().getIdBusiness());
				Business business = restTemplateBuilder.build().getForObject(GET_BUSINESS_BY_ID_API, Business.class, params2);
			    t.setBusiness(business);
			}
		});
		return otherTests.stream().map(test -> mapper.map(test, OtherTestDTO.class))
		.collect(Collectors.toList());
	}

	@Override
	public OtherTestDTO getTestById(String idTest) throws IOException {
		OtherTest test = testRepository.findByIdOtherTest(idTest);
		if(test!=null)
		{
			if(test.getBusiness()!=null) {
				Map<String, String> params2 = new HashMap<String, String>();
				params2.put("userId", test.getBusiness().getIdBusiness());
				Business business = restTemplateBuilder.build().getForObject(GET_BUSINESS_BY_ID_API, Business.class, params2);
				test.setBusiness(business);
			}
			return mapper.map(test, OtherTestDTO.class); 
		}
		else {
			throw new IOException("none exist");
		}
	}

	@Override
	public OtherTestDTO addTest(OtherTestDTO requsestTest, String idBusiness) {
		OtherTest test = mapper.map(requsestTest, OtherTest.class);
		   Map<String, String> params2 = new HashMap<String, String>();
			params2.put("userId", idBusiness);
			Business business = restTemplateBuilder.build().getForObject(GET_BUSINESS_BY_ID_API, Business.class, params2);
			test.setBusiness(business);
			
			OtherTest newTest = testRepository.save(test);
	       return mapper.map(newTest, OtherTestDTO.class);
	}

	@Override
	public OtherTestDTO updateTest(String idTest, OtherTestDTO requestTest) {
		OtherTest test = mapper.map(requestTest, OtherTest.class);
		OtherTest thetest = testRepository.findByIdOtherTest(idTest);
    	thetest.setIdOtherTest(idTest);
    	thetest.setCreationDate(test.getCreationDate());
    	thetest.setTitle(test.getTitle());
    	thetest.setQuestions(test.getQuestions());
    	thetest.setIntro(test.getIntro());
    	thetest.setRandomOrder(test.isRandomOrder());
    	Map<String, String> params2 = new HashMap<String, String>();
		params2.put("userId", thetest.getBusiness().getIdBusiness());
		Business business = restTemplateBuilder.build().getForObject(GET_BUSINESS_BY_ID_API, Business.class, params2);
		thetest.setBusiness(business);
		
    	if(thetest.getQuestions()!=null) {
    		thetest.getQuestions().forEach(q->q.setQuestionId(sequenceGeneratorService.generateSequence(QuestionsOtherTest.SEQUENCE_NAME)));
    	    
    	}
    	OtherTest newTest = testRepository.save(thetest);
		return mapper.map(newTest, OtherTestDTO.class);
	}

	@Override
	public void deleteTest(String idTest) {
		testRepository.deleteById(idTest);
		
	}

	@Override
	public List<QuestionsOtherTestDTO> getQuestionsByTest(String idTest) {
		OtherTest thetest = testRepository.findByIdOtherTest(idTest);
    	
    	return thetest.getQuestions().stream().map(q -> mapper.map(q, QuestionsOtherTestDTO.class))
    			.collect(Collectors.toList());
	}

	@Override
	public OtherTestDTO addTestQuestion(String idTest, QuestionsOtherTestDTO question) {
		  
		QuestionsOtherTest thisQuestion = mapper.map(question, QuestionsOtherTest.class);
  	  
  	  thisQuestion.setQuestionId(sequenceGeneratorService.generateSequence(QuestionsOtherTest.SEQUENCE_NAME));
  	  
  	  Query query = new Query();
  	  
          query.addCriteria(Criteria.where("idOtherTest").is(idTest));
          
          Update update = new Update();     
          
          update.addToSet("questions", thisQuestion );
            
          
        mongoTemplate.findAndModify(query, update, FindAndModifyOptions.options().upsert(false), OtherTest.class);
        OtherTest t  = testRepository.findByIdOtherTest(idTest);

  	   return  mapper.map(t, OtherTestDTO.class);
	}

	@Override
	public OtherTestDTO updateQuestion(String idTest, long idQuestion, QuestionsOtherTestDTO question) {
		OtherTest test  = testRepository.findByIdOtherTest(idTest); 
  	      test.getQuestions().forEach(q -> {   
  	    	  if(q.getQuestionId()==idQuestion) {
  	    	     q.setCorrectResponses(question.getCorrectResponses());
 	  	         q.setFalseResponses(question.getFalseResponses());
 	  	         q.setQuestionLabel(question.getQuestionLabel());
 	  	         q.setTypeQ(question.getTypeQ());
 	  	         
  	    	  }
  	    	     }
  	        );
  	      
  	      testRepository.save(test);
  	   
	       return mapper.map(test, OtherTestDTO.class);
	}

	@Override
	public OtherTestDTO deleteQestion(String idTest, long idQuestion) {
		OtherTest test  = testRepository.findByIdOtherTest(idTest); 
          ArrayList<QuestionsOtherTest> questions =new ArrayList<QuestionsOtherTest>();
          
  	       if(!test.getQuestions().isEmpty()) {
  	        test.getQuestions().forEach(q -> { 
  	    	  if(q.getQuestionId()==idQuestion) { 
  	    	    questions.addAll(test.getQuestions());
  	    	    questions.remove(q);
  	    			 	}
  	           }
  	       );}
  	       
  	       test.setQuestions(questions);
  	       testRepository.save(test);
  	       return  mapper.map(test, OtherTestDTO.class);
	}

	@Override
	public double testScore(String idOtherTest, OtherTestDTO test) {
		OtherTest t2 = mapper.map(test, OtherTest.class);
		OtherTest thetest = testRepository.findByIdOtherTest(idOtherTest);
    	int score=0;
    	for(int i=0;i<thetest.getQuestions().size();i++) {
    		if(thetest.getQuestions().get(i).getCorrectResponses().equals(t2.getQuestions().get(i).getCorrectResponses())) {
    			score++;
    		}
    		
    	}
    	return  ((double)(score*100)/thetest.getQuestions().size());
	}

}
