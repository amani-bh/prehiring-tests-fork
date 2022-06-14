package com.courzelo_for_business.prehiringtests.entities;

import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JobOffers {

	
	
	    
	    
	    private String idJob;

	   
	    private String title;

	    
	    private String description;
	    
	   
	    private Date creationDate;
	    
	    
	    private Date deadlineDate;
	    

	    private Date startDate;
	    
	   
	    private String industry;
	    
	   
	    private String subIndustry;


	    private String state;
	    
	    
	    private String jobType;
	    
	    
	    private String schedulesType;
	    
	    
	    private String location;
	    
	    
	    private String country;
	    
	     //online , onsite , hypride
	    private String locationType;
	    
	    
	    private List<String> requirement;

	    // number of people wanting to hire
	    private int hireNumber;
	    
	  
	    private String salaryOption;
	    
	    
	    private int salary;
	    
	    
	    private int salaryRangeMax;
	    
	    
	    private int salaryRangeMin;
	    
	    
	    private int salaryStartAmout;
	    
	    
	    private String salaryCurrency;
	    
	    
	    private String jobBenefits;
	    

	    private boolean communication;
	    
	    
	    private List<String> communicationMails;

	    
	    private Business business;
	    
	   
	    private String idPrehiringTest;
	    
	    
	    
		public JobOffers() {}


	    
		
		
	    
	    
	    
}

