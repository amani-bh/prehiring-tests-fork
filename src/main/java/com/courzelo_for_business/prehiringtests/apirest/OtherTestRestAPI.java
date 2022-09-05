package com.courzelo_for_business.prehiringtests.apirest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.courzelo_for_business.prehiringtests.entities.dtos.OtherTestDTO;
import com.courzelo_for_business.prehiringtests.entities.dtos.QuestionsOtherTestDTO;
import com.courzelo_for_business.prehiringtests.servicerest.iservicesrest.IServiceRestOtherTest;

@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping( "/OtherTests" )
@RestController
public class OtherTestRestAPI {

	@Autowired
	private IServiceRestOtherTest iTests;
	

	@GetMapping(path = "")
	public List<OtherTestDTO> getAllTests() {
		return iTests.getAllTests(); 
	}
	
	@GetMapping(path = "/business/{idBusiness}")
	public List<OtherTestDTO> getTestsByBusiness(@PathVariable(name = "idBusiness")  String idBusiness) {
		return iTests.getTestByBusiness(idBusiness); 
	}
	
	@GetMapping(path = "/businessCompanyName/{companyName}")
	public List<OtherTestDTO> getTestsByBusinessCompanyName(@PathVariable(name = "companyName")  String companyName) {
		return iTests.getTestByBusinessCompanyName(companyName); 
	}
	
	
	@GetMapping(path = "/{idOtherTest}")
    public ResponseEntity<?> getTest(@PathVariable(name = "idOtherTest")  String idOtherTest) {
		
		try {
			OtherTestDTO test=iTests.getTestById(idOtherTest);
			return new ResponseEntity<OtherTestDTO>(test, HttpStatus.CREATED);
			
		}
		catch(Exception e)
		{  
		   return new ResponseEntity<>( e.getMessage(),HttpStatus.ACCEPTED);
		}
		
		
		
        
    }
	
	
	@PostMapping(path = "/{idBusiness}")
    public ResponseEntity<OtherTestDTO> addTest(@RequestBody  @Valid  OtherTestDTO test,@PathVariable(name = "idBusiness") String idBusiness) {
		
		OtherTestDTO testResponse = iTests.addTest(test,idBusiness);
		
        return new ResponseEntity<OtherTestDTO>(testResponse, HttpStatus.CREATED);
    }

	
	@PutMapping(path = "/{idOtherTest}")
    public ResponseEntity<OtherTestDTO> updateTest(@PathVariable(name = "idOtherTest") String idOtherTest,@RequestBody  @Valid  OtherTestDTO test){
		
		OtherTestDTO testResponse = iTests.updateTest(idOtherTest,test);
		
        return new ResponseEntity<OtherTestDTO>(testResponse, HttpStatus.CREATED);
    }
	
	@DeleteMapping(path = "/{idOtherTest}")
	public void deleteTest(@PathVariable(name = "idOtherTest") String idOtherTest) {
		iTests.deleteTest(idOtherTest);
		
	}
	
	
	
	
	
	@GetMapping(path = "/Questions/{idOtherTest}")
	public List<QuestionsOtherTestDTO> getAllQuestions(@PathVariable(name = "idOtherTest") String idOtherTest) {
		return iTests.getQuestionsByTest(idOtherTest); 
		
	}
	
	@PostMapping(path = "/score/{idOtherTest}")
	public double getTestScore(@PathVariable(name = "idOtherTest") String idOtherTest,@RequestBody  @Valid  OtherTestDTO t) {
		return iTests.testScore(idOtherTest, t);
		
	}
	
	
	@PostMapping(path = "/Questions/{idOtherTest}")
    public ResponseEntity<OtherTestDTO> addQuestion(@PathVariable(name = "idOtherTest") String idOtherTest,@RequestBody  @Valid  QuestionsOtherTestDTO question) {
		
		OtherTestDTO testResponse = iTests.addTestQuestion(idOtherTest,question);
		
        return new ResponseEntity<OtherTestDTO>(testResponse, HttpStatus.CREATED);
    }
	
	
	@PutMapping(path = "/Questions/{idOtherTest}/{questionId}")
    public ResponseEntity<OtherTestDTO> updateQuestion(@PathVariable(name = "idOtherTest") String idOtherTest,@PathVariable(name = "questionId") long questionId,@RequestBody  @Valid  QuestionsOtherTestDTO response) {
		
		OtherTestDTO testResponse = iTests.updateQuestion(idOtherTest,questionId,response);
		
        return new ResponseEntity<OtherTestDTO>(testResponse, HttpStatus.CREATED);
    }
	
	
	
	//not done
	@DeleteMapping(path = "/Questions/{idOtherTest}/{questionId}")
    public ResponseEntity<OtherTestDTO> deleteQuestion(@PathVariable(name = "idOtherTest") String idOtherTest,@PathVariable(name = "questionId") long questionId){
		
		OtherTestDTO testResponse = iTests.deleteQestion(idOtherTest,questionId);
		
        return new ResponseEntity<OtherTestDTO>(testResponse, HttpStatus.CREATED);
    }
	
}
