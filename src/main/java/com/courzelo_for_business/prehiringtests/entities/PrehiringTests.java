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

@Document(collection="PrehiringTest")
@Getter
@Setter
@AllArgsConstructor
public class PrehiringTests {
	
	    
	    @Id
	    @Field(targetType = FieldType.OBJECT_ID,value = "idPrehiringTest")
	    private String idPrehiringTest;
	    
	    @Field(value = "title")
	    private String title;

	    @Field(value = "creationDate")
	    private Date creationDate;
	    
	    @Field(value = "type")
	    private String type;
	    
	    @Field(value = "level")
	    private String level;
	    
	    @Field(value = "intro")
	    private String intro;
	    
	    @Field(value = "deadline")
	    private Date deadline;
	    
	    @Field(value = "vdDate")
	    private Date vdDate;
	    
	    @Field(value = "questions")
	    private List<Questions> questions;
	    
	    @Field(value = "idBusiness")
	    private String idBusiness;

	    @Field(value = "openDate")
	    private Date openDate;
	    
	    @Field(value = "closeDate")
		private Date closeDate;
		  
	    @Field(value = "indefinitDate")
		private boolean indefinitDate;
	    
	    @Field(value = "randomOrder")
		private boolean randomOrder;
		
		

		public PrehiringTests() {
			super();
		}

		
		

		
	    
	    
	    

}
