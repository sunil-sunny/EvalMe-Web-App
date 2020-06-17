package com.group18.asdc.dao;

import com.group18.asdc.entities.BasicQuestionData;
import com.group18.asdc.entities.MultipleChoiceQuestion;
import com.group18.asdc.entities.User;

public interface CreateQuestionDao {
	
	public boolean isQuestionTitleExists(BasicQuestionData theBasicQuestionData);
	
	public boolean createQuestionTitle(BasicQuestionData basicQuestionData);
	
	public boolean createNumericOrTextQuestion(BasicQuestionData theBasicQuestion,User theUser);
	
	public boolean createMultipleChoiceQuestion(MultipleChoiceQuestion theMultipleChoiceChoose,User theUser);
	
	public int getIdForQuestionTitle(String questionTitle);
	
	public int getIdForQuestionType(String questionType);
	
	public int getQuestionId(BasicQuestionData theBasicQuestionData);

}
