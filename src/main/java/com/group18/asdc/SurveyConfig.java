package com.group18.asdc;

import static org.mockito.ArgumentMatchers.refEq;

import com.group18.asdc.dao.SurveyAnswerDao;
import com.group18.asdc.dao.SurveyAnswerDaoImpl;
import com.group18.asdc.dao.SurveyDao;
import com.group18.asdc.dao.SurveyDaoImpl;
import com.group18.asdc.service.SurveyAnswerServiceImpl;
import com.group18.asdc.service.SurveyAnswersService;
import com.group18.asdc.service.SurveyService;
import com.group18.asdc.service.SurveyServiceImpl;

public class SurveyConfig {

	private static SurveyConfig singletonInstance = null;
	private SurveyService theSurveyService;
	private SurveyDao theSurveyDao;
	private SurveyAnswerDao surveyAnswerDao;
	private SurveyAnswersService surveyAnswersService;

	private SurveyConfig() {
		this.theSurveyService = new SurveyServiceImpl();
		this.theSurveyDao = new SurveyDaoImpl();
		this.surveyAnswersService = new SurveyAnswerServiceImpl();
		this.surveyAnswerDao = new SurveyAnswerDaoImpl();
	}

	public static SurveyConfig getSingletonInstance() {
		if (null == singletonInstance) {
			singletonInstance = new SurveyConfig();
		}
		return singletonInstance;
	}

	public SurveyService getTheSurveyService() {
		return theSurveyService;
	}

	public void setTheSurveyService(SurveyService theSurveyService) {
		this.theSurveyService = theSurveyService;
	}

	public SurveyDao getTheSurveyDao() {
		return theSurveyDao;
	}

	public void setTheSurveyDao(SurveyDao theSurveyDao) {
		this.theSurveyDao = theSurveyDao;
	}

	public SurveyAnswerDao getSurveyAnswerDao()
	{
		return this.surveyAnswerDao;
	}

	public SurveyAnswersService getSurveyAnswersService()
	{
		return this.surveyAnswersService;
	}
}
