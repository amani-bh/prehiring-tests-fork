package com.courzelo_for_business.prehiringtests.entities.dtos;

import java.util.Date;
import java.util.List;

import com.courzelo_for_business.prehiringtests.entities.Business;
import com.courzelo_for_business.prehiringtests.entities.JobOffers;

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
  
    //private String type;
    
    //private String level;
 
    //private Date deadline;
  
    //private Date vdDate;

   
    
    //private Date openDate;
    
	//private Date closeDate;
	  
	//private boolean indefinitDate;
	
	
    
    
	public PrehiringTestsDTO() {
		super();
	}

	
	
	
}
