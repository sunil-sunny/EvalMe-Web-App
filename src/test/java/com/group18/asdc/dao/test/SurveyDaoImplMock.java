package com.group18.asdc.dao.test;

import java.util.ArrayList;
import java.util.List;

import com.group18.asdc.dao.SurveyDao;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.SurveyQuestion;

public class SurveyDaoImplMock implements SurveyDao {

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

}
