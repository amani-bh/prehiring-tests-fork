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

import com.courzelo_for_business.prehiringtests.entities.dtos.QuestionsDTO;
import com.courzelo_for_business.prehiringtests.entities.dtos.PrehiringTestsDTO;
import com.courzelo_for_business.prehiringtests.servicerest.iservicesrest.IServiceRestPrehiringTests;

@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping( "/PrehiringTests" )
@RestController
public class PrehiringTestsRestAPI {

	@Autowired
	private IServiceRestPrehiringTests iTests;
	

	@GetMapping(path = "")
	public List<PrehiringTestsDTO> getAllTests() {
		return iTests.getAllTests(); 
	}
	
	@GetMapping(path = "/business/{idBusiness}")
	public List<PrehiringTestsDTO> getTestsByBusiness(@PathVariable(name = "idBusiness")  String idBusiness) {
		return iTests.getTestByBusiness(idBusiness); 
	}
	
	
	@GetMapping(path = "/{idPrehiringTest}")
    public ResponseEntity<?> getTest(@PathVariable(name = "idPrehiringTest")  String idPrehiringTest) {
		
		try {
			PrehiringTestsDTO test=iTests.getTestById(idPrehiringTest);
			return new ResponseEntity<PrehiringTestsDTO>(test, HttpStatus.CREATED);
			
		}
		catch(Exception e)
		{  
		   return new ResponseEntity<>( e.getMessage(),HttpStatus.ACCEPTED);
		}
		
		
		
        
    }
	
	
	@PostMapping(path = "")
    public ResponseEntity<PrehiringTestsDTO> addTest(@RequestBody  @Valid  PrehiringTestsDTO test,@PathVariable(name = "idBusiness") String idBusiness) {
		
		PrehiringTestsDTO testResponse = iTests.addTest(test,idBusiness);
		
        return new ResponseEntity<PrehiringTestsDTO>(testResponse, HttpStatus.CREATED);
    }

	
	@PutMapping(path = "/{idPrehiringTest}")
    public ResponseEntity<PrehiringTestsDTO> updateTest(@PathVariable(name = "idPrehiringTest") String idPrehiringTest,@RequestBody  @Valid  PrehiringTestsDTO test){
		
		PrehiringTestsDTO testResponse = iTests.updateTest(idPrehiringTest,test);
		
        return new ResponseEntity<PrehiringTestsDTO>(testResponse, HttpStatus.CREATED);
    }
	
	@DeleteMapping(path = "/{idPrehiringTest}")
	public void deleteTest(@PathVariable(name = "idPrehiringTest") String idPrehiringTest) {
		iTests.deleteTest(idPrehiringTest);
		
	}
	
	
	
	
	
	@GetMapping(path = "/Questions/{idPrehiringTest}")
	public List<QuestionsDTO> getAllQuestions(@PathVariable(name = "idPrehiringTest") String idPrehiringTest) {
		return iTests.getQuestionsByTest(idPrehiringTest); 
		
	}
	
	@PostMapping(path = "/score/{idPrehiringTest}")
	public double getTestScore(@PathVariable(name = "idPrehiringTest") String idPrehiringTest,@RequestBody  @Valid  PrehiringTestsDTO t) {
		return iTests.testScore(idPrehiringTest, t);
		
	}
	
	
	@PostMapping(path = "/Questions/{idPrehiringTest}")
    public ResponseEntity<PrehiringTestsDTO> addQuestion(@PathVariable(name = "idPrehiringTest") String idPrehiringTest,@RequestBody  @Valid  QuestionsDTO question) {
		
		PrehiringTestsDTO testResponse = iTests.addTestQuestion(idPrehiringTest,question);
		
        return new ResponseEntity<PrehiringTestsDTO>(testResponse, HttpStatus.CREATED);
    }
	
	
	@PutMapping(path = "/Questions/{idPrehiringTest}/{questionId}")
    public ResponseEntity<PrehiringTestsDTO> updateQuestion(@PathVariable(name = "idPrehiringTest") String idPrehiringTest,@PathVariable(name = "questionId") long questionId,@RequestBody  @Valid  QuestionsDTO response) {
		
		PrehiringTestsDTO testResponse = iTests.updateQuestion(idPrehiringTest,questionId,response);
		
        return new ResponseEntity<PrehiringTestsDTO>(testResponse, HttpStatus.CREATED);
    }
	
	
	
	//not done
	@DeleteMapping(path = "/Questions/{idPrehiringTest}/{questionId}")
    public ResponseEntity<PrehiringTestsDTO> deleteQuestion(@PathVariable(name = "idPrehiringTest") String idPrehiringTest,@PathVariable(name = "questionId") long questionId){
		
		PrehiringTestsDTO testResponse = iTests.deleteQestion(idPrehiringTest,questionId);
		
        return new ResponseEntity<PrehiringTestsDTO>(testResponse, HttpStatus.CREATED);
    }
	
	

	
	

	

}
