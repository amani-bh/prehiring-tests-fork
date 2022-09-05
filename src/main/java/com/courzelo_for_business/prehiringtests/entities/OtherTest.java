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

@Document(collection="OtherTest")
@Getter
@Setter
@AllArgsConstructor
public class OtherTest {

	  @Id
	    @Field(targetType = FieldType.OBJECT_ID,value = "idOtherTest")
	    private String idOtherTest;
	    
	    @Field(value = "title")
	    private String title;

	    @Field(value = "creationDate")
	    private Date creationDate;
	    
	    @Field(value = "category")
	    private String category;
	    
	    @Field(value = "intro")
	    private String intro;
	    
	    @Field(value = "questions")
	    private List<QuestionsOtherTest> questions;
	    
	    @Field(value = "business")
	    private Business business;

	    @Field(value = "randomOrder")
		private boolean randomOrder;
	
		public OtherTest() {
			super();
		}

}
