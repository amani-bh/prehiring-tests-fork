package com.courzelo_for_business.prehiringtests.entities.dtos;

import java.util.Date;
import java.util.List;

import com.courzelo_for_business.prehiringtests.entities.Business;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PrehiringTestsDTO {

	
	
    private String idPrehiringTest;
   
    private String title;

    private Date creationDate;
    
    private String intro;
    
    private Business business;
   
    private boolean randomOrder;
    
    private List<QuestionsDTO> questions;
  
   
    
	public PrehiringTestsDTO() {
		super();
	}

	
	
	
}
