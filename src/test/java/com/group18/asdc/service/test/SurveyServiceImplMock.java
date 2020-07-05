package com.group18.asdc.service.test;

import java.util.ArrayList;
import java.util.List;

import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.QuestionMetaData;
import com.group18.asdc.entities.SurveyMetaData;
import com.group18.asdc.entities.SurveyQuestion;
import com.group18.asdc.errorhandling.QuestionExitsException;
import com.group18.asdc.errorhandling.SurveyAlreadyPublishedException;
import com.group18.asdc.service.SurveyService;

public class SurveyServiceImplMock implements SurveyService{

	@Override
	public boolean addQuestionToSurvey(QuestionMetaData theQuestionMetaData) throws QuestionExitsException {
		List<QuestionMetaData> questionList=new ArrayList<QuestionMetaData>();
		questionList.add(theQuestionMetaData);
		return true;
	}

	@Override
	public List<SurveyQuestion> getAllSavedSurveyQuestions(Course course) {
		List<SurveyQuestion> surveyQuestions=new ArrayList<SurveyQuestion>();
		surveyQuestions.add(new SurveyQuestion());
		surveyQuestions.add(new SurveyQuestion());
		return surveyQuestions;
	}

	@Override
	public boolean saveSurveyQuestions(List<SurveyQuestion> allSurveyQuestions) {
		List<SurveyQuestion> surveyQuestions=new ArrayList<SurveyQuestion>();
		surveyQuestions.addAll(allSurveyQuestions);
		return true;
	}

	@Override
	public boolean publishSurvey() throws SurveyAlreadyPublishedException {
		SurveyMetaData surveyMetaData=new SurveyMetaData();
		surveyMetaData.setPublishedStatus(true);
		return surveyMetaData.isPublishedStatus();
	}

	@Override
	public boolean removeQuestionFromSurvey(QuestionMetaData theQuestionMetaData) {
		List<SurveyQuestion> surveyQuestions=new ArrayList<SurveyQuestion>();
		surveyQuestions.add(new SurveyQuestion());
		surveyQuestions.add(new SurveyQuestion());
		surveyQuestions.remove(1);
		return true;
	}

	@Override
	public List<SurveyQuestion> getCurrentListOfSurveyQuestions() {
		List<SurveyQuestion> surveyQuestions=new ArrayList<SurveyQuestion>();
		surveyQuestions.add(new SurveyQuestion());
		surveyQuestions.add(new SurveyQuestion());
		return surveyQuestions;
	}

	@Override
	public boolean isSurveyPublishedForCourse(Course theCourse) {
		SurveyMetaData surveyMetaData=new SurveyMetaData();
		surveyMetaData.setPublishedStatus(true);
		return surveyMetaData.isPublishedStatus();
	}

}
