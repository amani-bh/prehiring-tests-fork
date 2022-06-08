package com.courzelo_for_business.prehiringtests.servicerest.iservicesrest;

import java.io.IOException;
import java.util.List;

import com.courzelo_for_business.prehiringtests.entities.dtos.QuestionsDTO;
import com.courzelo_for_business.prehiringtests.entities.dtos.PrehiringTestsDTO;



public interface IServiceRestPrehiringTests {
	

	public List<PrehiringTestsDTO> getAllTests();
	public List<PrehiringTestsDTO> getTestByBusiness(String idBusiness);
	public PrehiringTestsDTO getTestById(String idTest) throws IOException;
	
	public PrehiringTestsDTO addTest(PrehiringTestsDTO requsestTest);
	public PrehiringTestsDTO updateTest(String idTest , PrehiringTestsDTO requestTest); 
	public void deleteTest(String idTest); 

	public List<QuestionsDTO> getQuestionsByTest(String idTest);
	public PrehiringTestsDTO addTestQuestion(String idTest,QuestionsDTO question);
	public PrehiringTestsDTO updateQuestion(String idTest, long idQuestion , QuestionsDTO question);
	public PrehiringTestsDTO deleteQestion(String idTest, long idQuestion);
	public double testScore(String idPrehiringTest,PrehiringTestsDTO test);
	
}
