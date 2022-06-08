package com.courzelo_for_business.prehiringtests.servicerest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.courzelo_for_business.prehiringtests.entities.PrehiringTests;
import com.courzelo_for_business.prehiringtests.entities.Questions;
import com.courzelo_for_business.prehiringtests.entities.dtos.QuestionsDTO;
import com.courzelo_for_business.prehiringtests.entities.dtos.PrehiringTestsDTO;
import com.courzelo_for_business.prehiringtests.repositories.PrehiringTestsRepository;
import com.courzelo_for_business.prehiringtests.servicerest.iservicesrest.IServiceRestPrehiringTests;


@Service
public class PrehiringTestsRestService implements IServiceRestPrehiringTests {

	
	    @Autowired
	    private ModelMapper mapper;
	    
	    @Autowired
	    private  SequenceGeneratorService sequenceGeneratorService;
	   
	    @Autowired
	    private  PrehiringTestsRepository testRepository;
	    
	    @Autowired 
	    private MongoTemplate mongoTemplate;

	    
	    @Override
		public List<PrehiringTestsDTO> getAllTests() {
			
			
			List<PrehiringTests> prehiringTests = testRepository.findAll();
			return prehiringTests.stream().map(test -> mapper.map(test, PrehiringTestsDTO.class))
			.collect(Collectors.toList());
		}
	    
	    public List<PrehiringTestsDTO> getTestByBusiness(String idBusiness){
	    	List<PrehiringTests> prehiringTests = testRepository.findByIdBusiness(idBusiness);
			return prehiringTests.stream().map(test -> mapper.map(test, PrehiringTestsDTO.class))
			.collect(Collectors.toList());
	    }
	    
	    
	    @Override
		public PrehiringTestsDTO getTestById(String idPrehiringTest) throws IOException {
			PrehiringTests test = testRepository.findByIdPrehiringTest(idPrehiringTest);
			if(test!=null)
			{
				return mapper.map(test, PrehiringTestsDTO.class); 
			}
			else {
				throw new IOException("none exist");
			}
			
			
		}
	    @Override
		public PrehiringTestsDTO addTest(PrehiringTestsDTO requestTest) {
		   PrehiringTests test = mapper.map(requestTest, PrehiringTests.class);
		   PrehiringTests newTest = testRepository.save(test);
	       return mapper.map(newTest, PrehiringTestsDTO.class);
			
		}
	    
	    
	    @Override
		public PrehiringTestsDTO updateTest(String idPrehiringTest , PrehiringTestsDTO requestTest) {
			
	    	PrehiringTests test = mapper.map(requestTest, PrehiringTests.class);
	    	PrehiringTests thetest = testRepository.findByIdPrehiringTest(idPrehiringTest);
	    	thetest.setIdPrehiringTest(idPrehiringTest);
	    	thetest.setCreationDate(test.getCreationDate());
	    	thetest.setDeadline(test.getDeadline());
	    	thetest.setTitle(test.getTitle());
	    	thetest.setType(test.getType());
	    	thetest.setVdDate(test.getVdDate());
	    	thetest.setIdBusiness(test.getIdBusiness());
	    	thetest.setQuestions(test.getQuestions());
	    	thetest.setIntro(test.getIntro());
	    	thetest.setLevel(test.getLevel());
	    	thetest.setOpenDate(test.getOpenDate());
	    	thetest.setIndefinitDate(test.isIndefinitDate());
	    	thetest.setCloseDate(test.getCloseDate());
	    	thetest.setRandomOrder(test.isRandomOrder());
	    	thetest.getQuestions().forEach(q->q.setQuestionId(sequenceGeneratorService.generateSequence(Questions.SEQUENCE_NAME)));
	    	PrehiringTests newTest = testRepository.save(thetest);
			return mapper.map(newTest, PrehiringTestsDTO.class);
			
			
		}
	    
	    
	    public void deleteTest(String idPrehiringTest){
			
	    	testRepository.deleteById(idPrehiringTest);
			}
	    
	    
		
	    
	    public List<QuestionsDTO> getQuestionsByTest(String idPrehiringTest) {
	    	
	    	PrehiringTests thetest = testRepository.findByIdPrehiringTest(idPrehiringTest);
	    	
	    	return thetest.getQuestions().stream().map(q -> mapper.map(q, QuestionsDTO.class))
	    			.collect(Collectors.toList());
	    }
	    
	    
	    
	    
	    public  PrehiringTestsDTO addTestQuestion(String idPrehiringTest, QuestionsDTO question){
		
	    	   
	    	  
	    	  
	    	  Questions thisQuestion = mapper.map(question, Questions.class);
	    	  
	    	  thisQuestion.setQuestionId(sequenceGeneratorService.generateSequence(Questions.SEQUENCE_NAME));
	    	  
	    	  Query query = new Query();
	    	  
  	          query.addCriteria(Criteria.where("idPrehiringTest").is(idPrehiringTest));
  	          
  	          Update update = new Update();     
  	          
  	          update.addToSet("questions", thisQuestion );
  	            
  	          
  	        mongoTemplate.findAndModify(query, update, FindAndModifyOptions.options().upsert(false), PrehiringTests.class);
  	        PrehiringTests t  = testRepository.findByIdPrehiringTest(idPrehiringTest);

	    	   return  mapper.map(t, PrehiringTestsDTO.class);
				
			}
	    
	    public PrehiringTestsDTO updateQuestion(String idPrehiringTest, long questionId,QuestionsDTO question){
	    	  
 	          PrehiringTests test  = testRepository.findByIdPrehiringTest(idPrehiringTest); 
 	  	      test.getQuestions().forEach(q -> {   
 	  	    	  if(q.getQuestionId()==questionId) {
 	  	    	     q.setCorrectResponses(question.getCorrectResponses());
 	 	  	         q.setFalseResponses(question.getFalseResponses());
 	 	  	         q.setQuestionLabel(question.getQuestionLabel());
 	 	  	         q.setTypeQ(question.getTypeQ());
 	 	  	         
 	  	    	  }
 	  	    	     }
 	  	        );
 	  	      
 	  	      testRepository.save(test);
 	  	   
		       return mapper.map(test, PrehiringTestsDTO.class);
				
			}
		
	    
	    
	    public PrehiringTestsDTO deleteQestion(String idPrehiringTest, long questionId){
			
	    	  
 	          PrehiringTests test  = testRepository.findByIdPrehiringTest(idPrehiringTest); 
 	          ArrayList<Questions> questions =new ArrayList<Questions>();
 	          
 	        	
 	          
 	  	         
 	  	       if(!test.getQuestions().isEmpty()) {
 	  	        test.getQuestions().forEach(q -> { 
 	  	    	  if(q.getQuestionId()==questionId) { 
 	  	    	    questions.addAll(test.getQuestions());
 	  	    	    questions.remove(q);
 	  	    			 	}
 	  	           }
 	  	       );}
 	  	       
 	  	       test.setQuestions(questions);
 	  	       testRepository.save(test);
 	  	       
 	  	        
 	  	      
 	  	     
	  	    	return  mapper.map(test, PrehiringTestsDTO.class);
 	  	     
			}
	    
 public double testScore(String idPrehiringTest,PrehiringTestsDTO test) {
	        PrehiringTests t2 = mapper.map(test, PrehiringTests.class);
	    	PrehiringTests thetest = testRepository.findByIdPrehiringTest(idPrehiringTest);
	    	int score=0;
	    	for(int i=0;i<thetest.getQuestions().size();i++) {
	    		if(thetest.getQuestions().get(i).getCorrectResponses().equals(t2.getQuestions().get(i).getCorrectResponses())) {
	    			score++;
	    		}
	    		
	    	}
	    	return  ((double)(score*100)/thetest.getQuestions().size());
	    }
	    
	   
}
