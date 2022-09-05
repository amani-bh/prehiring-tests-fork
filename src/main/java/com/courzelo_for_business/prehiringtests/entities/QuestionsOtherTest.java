package com.courzelo_for_business.prehiringtests.entities;

import java.util.List;

import org.springframework.data.annotation.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QuestionsOtherTest {

	  @Transient
      public static final String SEQUENCE_NAME = "oquestions_sequence";
	  private long questionId;
	  private String questionLabel;
	  private String categoryQuestion;
	  private List<String> falseResponses; ////list of false responses for each question 
	  private List<String> correctResponses; ////list of false responses for each question 
	  private int score ;
	  private int time;
	  private String typeQ;	 
	  public QuestionsOtherTest() {  
		  super();
		   }
}
