package com.courzelo_for_business.prehiringtests.servicerest.iservicesrest;

import java.io.IOException;
import java.util.List;

import com.courzelo_for_business.prehiringtests.entities.dtos.OtherTestDTO;
import com.courzelo_for_business.prehiringtests.entities.dtos.QuestionsOtherTestDTO;

public interface IServiceRestOtherTest {
	

	public List<OtherTestDTO> getAllTests();
	public List<OtherTestDTO> getTestByBusiness(String idBusiness);
	public List<OtherTestDTO> getTestByBusinessCompanyName(String companyName);
	public OtherTestDTO getTestById(String idTest) throws IOException;
	
	public OtherTestDTO addTest(OtherTestDTO requsestTest,String idBusiness);
	public OtherTestDTO updateTest(String idTest , OtherTestDTO requestTest); 
	public void deleteTest(String idTest); 

	public List<QuestionsOtherTestDTO> getQuestionsByTest(String idTest);
	public OtherTestDTO addTestQuestion(String idTest,QuestionsOtherTestDTO question);
	public OtherTestDTO updateQuestion(String idTest, long idQuestion , QuestionsOtherTestDTO question);
	public OtherTestDTO deleteQestion(String idTest, long idQuestion);
	public double testScore(String idOtherTest,OtherTestDTO test);
	
}