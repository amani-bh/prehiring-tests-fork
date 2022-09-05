package com.courzelo_for_business.prehiringtests.entities.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QuestionsOtherTestDTO {
	  private long questionId;
	  private String questionLabel;
	  private String categoryQuestion;
	  private List<String> falseResponses; ////list of false responses for each question 
	  private List<String> correctResponses; ////list of false responses for each question 
	  private int score ;
	  private int time;
	  private String typeQ;

	 
	  public QuestionsOtherTestDTO() {
		 super();
	  }
}
