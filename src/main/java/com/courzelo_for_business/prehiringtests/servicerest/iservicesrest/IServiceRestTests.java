package com.courzelo_for_business.prehiringtests.servicerest.iservicesrest;

import java.util.List;
import com.courzelo_for_business.prehiringtests.entities.dtos.TestsDTO;

public interface IServiceRestTests {

	
	public TestsDTO addTest(TestsDTO test,String idBusiness);
	public List<TestsDTO> getAllTests();
	 public List<TestsDTO> getByBusiness(String idBusiness );
	 public List<TestsDTO> getByBusinessCompanyName(String companyName );
	public TestsDTO updateTest(String idTest , TestsDTO requestTest);
	public void deleteTest(String idTest);
}
