package com.courzelo_for_business.prehiringtests;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.courzelo_for_business.prehiringtests.entities.dtos.TestsDTO;
import com.courzelo_for_business.prehiringtests.servicerest.iservicesrest.IServiceRestTests;

@RunWith(SpringRunner.class)
@SpringBootTest

public class TestsApplication {
	
	@Autowired
	private IServiceRestTests iTest;
	
	@Test
    public void GetTests()
    {       List<TestsDTO> tests=iTest.getAllTests();
            Assert.assertEquals(3,tests.size());
    }

	
	@Test
    public void GetTestsByBusiness()
    {       List<TestsDTO> tests=iTest.getByBusiness("626b2efbf1d5b22ee0106932");
            Assert.assertEquals(3,tests.size());
    }
	
	@Test
    public void AddTest()
    {   
		TestsDTO test=new TestsDTO();
		test.setDateCreation(new Date());
		test.setIdQuiz("123");
		iTest.addTest(test,"62a7bd332a97437691661cf9");    
		List<TestsDTO> tests=iTest.getByBusiness("62a7bd332a97437691661cf9");
        Assert.assertEquals(1,tests.size());
        
    }

	
}
