package com.group18.asdc.dao.test;

import java.util.ArrayList;
import java.util.List;

import com.group18.asdc.dao.CreateQuestionDao;
import com.group18.asdc.entities.BasicQuestionData;
import com.group18.asdc.entities.MultipleChoiceQuestion;
import com.group18.asdc.entities.User;

public class CreateQuestionsDaoImplMock implements CreateQuestionDao {

	private static List<String> titles = new ArrayList<String>();
	private static List<String> types=new ArrayList<String>();
	private static List<BasicQuestionData> numberOrTextQuestions = new ArrayList<BasicQuestionData>();
	private static List<MultipleChoiceQuestion> multipleQuestions = new ArrayList<MultipleChoiceQuestion>();

	public CreateQuestionsDaoImplMock() {

		//adding titles to list
		CreateQuestionsDaoImplMock.titles.add("java");
		CreateQuestionsDaoImplMock.titles.add("python");
		
		//create question types
		CreateQuestionsDaoImplMock.types.add("freetext");
		CreateQuestionsDaoImplMock.types.add("multiple-choice-choose-one");
		CreateQuestionsDaoImplMock.types.add("multiple-choice-choose-many");
		CreateQuestionsDaoImplMock.types.add("numericquestions");
		
		
	}

	@Override
	public boolean isQuestionTitleExists(BasicQuestionData theBasicQuestionData) {
		
		String questionTitle=theBasicQuestionData.getQuestionTitle();
		
		for(String title:CreateQuestionsDaoImplMock.titles) {
			if(title.equalsIgnoreCase(questionTitle)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean createQuestionTitle(BasicQuestionData basicQuestionData) {
		
		CreateQuestionsDaoImplMock.titles.add(basicQuestionData.getQuestionTitle());
		
		return true;
	}

	@Override
	public boolean createNumericOrTextQuestion(BasicQuestionData theBasicQuestionData, User theUser) {

		CreateQuestionsDaoImplMock.numberOrTextQuestions.add(theBasicQuestionData);
 
		return true;
	}

	@Override
	public int getIdForQuestionTitle(String questionTitle) {

		int index=CreateQuestionsDaoImplMock.titles.indexOf(questionTitle);
		return index;
	}

	@Override
	public int getIdForQuestionType(String questionType) {

		int index=CreateQuestionsDaoImplMock.types.indexOf(questionType);
		
		return index;
	}

	@Override
	public boolean createMultipleChoiceQuestion(MultipleChoiceQuestion theMultipleChoiceChoose, User theUser) {

		CreateQuestionsDaoImplMock.multipleQuestions.add(theMultipleChoiceChoose);
		return true;
	}

	@Override
	public int getQuestionId(BasicQuestionData theBasicQuestionData) {
		
		String questionTitle=theBasicQuestionData.getQuestionTitle();
		if(questionTitle==null) {
			return 1;
		}
		return 1;
	}

}
