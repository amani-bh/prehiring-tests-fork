package com.courzelo_for_business.prehiringtests.entities.dtos;

import java.util.Date;
import java.util.List;
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
  
    private String type;
 
    private Date deadline;
  
    private Date vdDate;

    private String idBusiness;
    
    
    private String level;
    
    
    private String intro;
    
    private Date openDate;
    
	private Date closeDate;
	  
	private boolean indefinitDate;
	
	private boolean randomOrder;
    
    private List<QuestionsDTO> questions;
    
    
	public PrehiringTestsDTO() {
		super();
	}

	
	
	
}
