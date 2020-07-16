package com.group18.asdc.entities.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import com.group18.asdc.entities.BasicQuestionData;
import com.group18.asdc.entities.Option;
import com.group18.asdc.entities.QuestionMetaData;
import com.group18.asdc.entities.SurveyQuestion;

public class SurveyQuestionTest {

	@Test
	public void getLogicDetail() {
		SurveyQuestion surveyQuestion = new SurveyQuestion();
		surveyQuestion.setLogicDetail("group dissimilar");
		assertTrue(surveyQuestion.getLogicDetail().equals("group dissimilar"));
	}
	
	@Test
	public void setLogicDetail() {
		SurveyQuestion surveyQuestion = new SurveyQuestion();
		surveyQuestion.setLogicDetail("group dissimilar");
		assertTrue(surveyQuestion.getLogicDetail().equals("group dissimilar"));
	}
	
	@Test
	public void getLogicConstraint() {
		SurveyQuestion surveyQuestion = new SurveyQuestion();
		surveyQuestion.setLogicConstraint(3);
		assertTrue(surveyQuestion.getLogicConstraint()==3);
	}
	
	@Test
	public void setLogicConstraint() {
		SurveyQuestion surveyQuestion = new SurveyQuestion();
		surveyQuestion.setLogicConstraint(3);
		assertTrue(surveyQuestion.getLogicConstraint()==3);
	}

	@Test
	public void getSurveyQuestionId() {
		SurveyQuestion surveyQuestion = new SurveyQuestion();
		surveyQuestion.setSurveyQuestionId(4);
		assertTrue(surveyQuestion.getSurveyQuestionId()==4);
	}

	@Test
	public void setSurveyQuestionId() {
		SurveyQuestion surveyQuestion = new SurveyQuestion();
		surveyQuestion.setSurveyQuestionId(4);
		assertTrue(surveyQuestion.getSurveyQuestionId()==4);	
	}

	@Test
	public void getQuestionData() {
		SurveyQuestion surveyQuestion = new SurveyQuestion();
		QuestionMetaData questionMetaData = new QuestionMetaData();
		questionMetaData.setQuestionId(24);
		surveyQuestion.setQuestionData(questionMetaData);
		BasicQuestionData basicQuestionData = new BasicQuestionData();
		basicQuestionData.setQuestionText("Describe an experience you had with Java");
		basicQuestionData.setQuestionTitle("Java and Data Structures");
		basicQuestionData.setQuestionType("freetext");
		questionMetaData.setBasicQuestionData(basicQuestionData);
		
		surveyQuestion.setQuestionData(questionMetaData);
		assertTrue(surveyQuestion.getQuestionData().equals(questionMetaData));
	}

	@Test
	public void setQuestionData() {
		SurveyQuestion surveyQuestion = new SurveyQuestion();
		QuestionMetaData questionMetaData = new QuestionMetaData();
		questionMetaData.setQuestionId(24);
		surveyQuestion.setQuestionData(questionMetaData);
		BasicQuestionData basicQuestionData = new BasicQuestionData();
		basicQuestionData.setQuestionText("Describe an experience you had with Java");
		basicQuestionData.setQuestionTitle("Java and Data Structures");
		basicQuestionData.setQuestionType("freetext");
		questionMetaData.setBasicQuestionData(basicQuestionData);
		
		surveyQuestion.setQuestionData(questionMetaData);
		assertTrue(surveyQuestion.getQuestionData().equals(questionMetaData));
	}

	@Test
	public void getPriority() {
		SurveyQuestion surveyQuestion = new SurveyQuestion();
		surveyQuestion.setPriority(6);
		assertTrue(surveyQuestion.getPriority()==6);
	}
	
	@Test
	public void setPriority() {
		SurveyQuestion surveyQuestion = new SurveyQuestion();
		surveyQuestion.setPriority(6);
		assertTrue(surveyQuestion.getPriority()==6);
	}
	
	@Test
	public void getOptions() {
		SurveyQuestion surveyQuestion = new SurveyQuestion();
		Option option = new Option();
		List<Option> optionList = new ArrayList<Option>();
		option.setDisplayText("Beginner");
		option.setStoredData(1);
		optionList.add(option);
		surveyQuestion.setOptions(optionList);
		assertTrue(surveyQuestion.getOptions().equals(optionList));
	}
	
	@Test
	public void setOptions() {
		SurveyQuestion surveyQuestion = new SurveyQuestion();
		Option option = new Option();
		List<Option> optionList = new ArrayList<Option>();
		option.setDisplayText("Beginner");
		option.setStoredData(1);
		optionList.add(option);
		surveyQuestion.setOptions(optionList);
		assertTrue(surveyQuestion.getOptions().equals(optionList));
	}
}