package com.courzelo_for_business.prehiringtests.entities.dtos;

import java.util.Date;

import com.courzelo_for_business.prehiringtests.entities.Business;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TestsDTO {

	private String idTest;

    private String idQuiz;
    
    private Business business;
   
    private String type;
    
    private Date dateCreation;
    
    

	public TestsDTO() {
		
	}
    
    
}
