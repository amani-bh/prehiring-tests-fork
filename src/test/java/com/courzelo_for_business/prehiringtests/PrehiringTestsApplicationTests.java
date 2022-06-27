package com.courzelo_for_business.prehiringtests;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.courzelo_for_business.prehiringtests.entities.dtos.PrehiringTestsDTO;
import com.courzelo_for_business.prehiringtests.entities.dtos.QuestionsDTO;
import com.courzelo_for_business.prehiringtests.servicerest.iservicesrest.IServiceRestPrehiringTests;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PrehiringTestsApplicationTests {

	@Autowired
	IServiceRestPrehiringTests iServiceprehiring;

	
	@Test
    public void getJobs()
    {       List<PrehiringTestsDTO> tests=iServiceprehiring.getAllTests();
            Assert.assertEquals(0,tests.size());
    }
	
	
	 public void getJobsByBusiness()
	    {       List<PrehiringTestsDTO> tests=iServiceprehiring.getTestByBusiness("626b2efbf1d5b22ee0106932");
	            Assert.assertEquals(0,tests.size());
	    }
	 
	 public void getQuestionByTest()
	    {       List<QuestionsDTO> question=iServiceprehiring.getQuestionsByTest("626b2efbf1d5b22ee0106932");
	            Assert.assertEquals(0,question.size());
	    }
	 
	@Test
    public void addTest()
    {       PrehiringTestsDTO test=new PrehiringTestsDTO(); 
            test.setCreationDate(new Date());
            iServiceprehiring.addTest(test,"6261e803e22a337c2ec32e05");
            List<PrehiringTestsDTO> tests=iServiceprehiring.getAllTests();
            Assert.assertEquals(1,tests.size());
		    
           
    }
	
}
