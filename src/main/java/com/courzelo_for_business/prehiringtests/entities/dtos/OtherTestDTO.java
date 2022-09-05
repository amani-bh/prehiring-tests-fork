package com.courzelo_for_business.prehiringtests.entities.dtos;

import java.util.Date;
import java.util.List;

import com.courzelo_for_business.prehiringtests.entities.Business;
import com.courzelo_for_business.prehiringtests.entities.Questions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OtherTestDTO {


    private String idOtherTest;
   
    private String title;

    private Date creationDate;
    
    private String intro;
    
    private Business business;
   
    private boolean randomOrder;
    
    private String category;
    
    private List<QuestionsOtherTestDTO> questions;
  
   
    
	public OtherTestDTO() {
		super();
	}

}
