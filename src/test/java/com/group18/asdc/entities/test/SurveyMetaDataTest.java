package com.group18.asdc.entities.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.QuestionMetaData;
import com.group18.asdc.entities.SurveyMetaData;
import com.group18.asdc.entities.SurveyQuestion;
import com.group18.asdc.entities.User;

public class SurveyMetaDataTest {

	@Test
	public void getSurveyId() {
		SurveyMetaData surveyMetaData = new SurveyMetaData();
		surveyMetaData.setSurveyId(1);
		assertTrue(surveyMetaData.getSurveyId()==1);
	}
	
	@Test
	public void setSurveyId() {
		SurveyMetaData surveyMetaData = new SurveyMetaData();
		surveyMetaData.setSurveyId(1);
		assertTrue(surveyMetaData.getSurveyId()==1);
	}
	
	@Test
	public void getSurveyQuestions() {
		SurveyQuestion surveyQuestion = new SurveyQuestion();
		surveyQuestion.setSurveyQuestionId(256);
		surveyQuestion.setLogicConstraint(1);
		surveyQuestion.setLogicDetail("group dissimilar");
		surveyQuestion.setPriority(1);
		QuestionMetaData questionMetaData = new QuestionMetaData();
		questionMetaData.setQuestionId(69);
		surveyQuestion.setQuestionData(questionMetaData);
		SurveyMetaData surveyMetaData = new SurveyMetaData();
		List<SurveyQuestion> surveyQuestionList = new ArrayList<SurveyQuestion>();
		surveyQuestionList.add(surveyQuestion);
		surveyMetaData.setSurveyQuestions(surveyQuestionList);
	}
	
	@Test
	public void setSurveyQuestions() {
		SurveyQuestion surveyQuestion = new SurveyQuestion();
		surveyQuestion.setSurveyQuestionId(256);
		surveyQuestion.setLogicConstraint(1);
		surveyQuestion.setLogicDetail("group dissimilar");
		surveyQuestion.setPriority(1);
		QuestionMetaData questionMetaData = new QuestionMetaData();
		questionMetaData.setQuestionId(69);
		surveyQuestion.setQuestionData(questionMetaData);
		SurveyMetaData surveyMetaData = new SurveyMetaData();
		List<SurveyQuestion> surveyQuestionList = new ArrayList<SurveyQuestion>();
		surveyQuestionList.add(surveyQuestion);
		surveyMetaData.setSurveyQuestions(surveyQuestionList);
	}
	
	@Test
	public void isPublishedStatus() {
		SurveyMetaData surveyMetaData = new SurveyMetaData();
		surveyMetaData.setPublishedStatus(true);
		assertTrue(surveyMetaData.isPublishedStatus());	
	}
	
	@Test
	public void setPublishedStatus() {
		SurveyMetaData surveyMetaData = new SurveyMetaData();
		surveyMetaData.setPublishedStatus(true);
		assertTrue(surveyMetaData.isPublishedStatus());	
	}
	
	@Test
	public void getGroupSize() {
		SurveyMetaData surveyMetaData = new SurveyMetaData();
		surveyMetaData.setGroupSize(4);
		assertTrue(surveyMetaData.getGroupSize()==4);
	}
	
	@Test
	public void setGroupSize() {
		SurveyMetaData surveyMetaData = new SurveyMetaData();
		surveyMetaData.setGroupSize(4);
		assertTrue(surveyMetaData.getGroupSize()==4);
	}
	
	@Test
	public void getTheCourse() {
		SurveyMetaData surveyMetaData = new SurveyMetaData();
		Course course = new Course();
		course.setCourseId(1995);
		course.setCourseName("Java and Data Structures");
		User user = new User();
		user.setBannerId("B00842470");
		course.setInstructorName(user);
		surveyMetaData.setTheCourse(course);
		assertTrue(surveyMetaData.getTheCourse().equals(course));
	}
	
	@Test
	public void setTheCourse() {
		SurveyMetaData surveyMetaData = new SurveyMetaData();
		Course course = new Course();
		course.setCourseId(1995);
		course.setCourseName("Java and Data Structures");
		User user = new User();
		user.setBannerId("B00842470");
		course.setInstructorName(user);
		surveyMetaData.setTheCourse(course);
		assertTrue(surveyMetaData.getTheCourse().equals(course));
	}
}
