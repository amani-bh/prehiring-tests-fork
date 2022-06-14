package com.courzelo_for_business.prehiringtests;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.courzelo_for_business.prehiringtests.entities.Business;
import com.courzelo_for_business.prehiringtests.entities.JobOffers;
import com.courzelo_for_business.prehiringtests.entities.dtos.PrehiringTestsDTO;
import com.courzelo_for_business.prehiringtests.entities.dtos.QuestionsDTO;
import com.courzelo_for_business.prehiringtests.servicerest.iservicesrest.IServiceRestPrehiringTests;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PrehiringTestsApplicationTests {

	@Autowired
	IServiceRestPrehiringTests iServiceprehiring;

	
	/*@Test
    public void GetJobs()
    {       List<PrehiringTestsDTO> tests=iServiceprehiring.getAllTests();
            Assert.assertEquals(tests.size(),0);
    }
	
	
	 public void GetJobsByBusiness()
	    {       List<PrehiringTestsDTO> tests=iServiceprehiring.getTestByBusiness("626b2efbf1d5b22ee0106932");
	            Assert.assertEquals(tests.size(),0);
	    }
	 
	 public void GetQuestionByTest()
	    {       List<QuestionsDTO> question=iServiceprehiring.getQuestionsByTest("626b2efbf1d5b22ee0106932");
	            Assert.assertEquals(question.size(),0);
	    }*/
	 
	@Test
    public void AddTest()
    {       PrehiringTestsDTO test=new PrehiringTestsDTO(); 
            test.setCreationDate(new Date());
            PrehiringTestsDTO res=iServiceprehiring.addTest(test,"6261e803e22a337c2ec32e05");
            List<PrehiringTestsDTO> tests=iServiceprehiring.getAllTests();
            Assert.assertEquals(tests.size(),1);
		    
            
    }
	
}
