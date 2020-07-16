package com.group18.asdc.entities.test;

import com.group18.asdc.entities.Answer;
import com.group18.asdc.entities.BasicQuestionData;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.Group;
import com.group18.asdc.entities.MultipleChoiceQuestion;
import com.group18.asdc.entities.Option;
import com.group18.asdc.entities.PasswordHistory;
import com.group18.asdc.entities.QuestionMetaData;
import com.group18.asdc.entities.SurveyGroups;
import com.group18.asdc.entities.SurveyList;
import com.group18.asdc.entities.SurveyMetaData;
import com.group18.asdc.entities.SurveyQuestion;
import com.group18.asdc.entities.User;
import com.group18.asdc.entities.UserRegistartionDetails;

public class ModelTestAbstractFactoryImpl implements ModelTestAbstractFactory {

	@Override
	public BasicQuestionData getBasicQuestionDataTest() {
		return new BasicQuestionData();
	}

	@Override
	public Option getOptionTest() {
		return new Option();
	}

	@Override
	public Course getCourseTest() {
		return new Course();
	}

	@Override
	public MultipleChoiceQuestion getMultipleChoiceQuestionTest() {
		return new MultipleChoiceQuestion();
	}

	@Override
	public PasswordHistory getPasswordHistoryTest() {
		return new PasswordHistory();
	}

	@Override
	public QuestionMetaData getQuestionMetaDataTest() {
		return new QuestionMetaData();
	}

	@Override
	public SurveyMetaData getSurveyMetaDataTest() {
		return new SurveyMetaData();
	}

	@Override
	public UserRegistartionDetails getIUserRegistartionDetailsTest() {
		return new UserRegistartionDetails();
	}

	@Override
	public User getUserTest() {
		return new User();
	}

	@Override
	public SurveyQuestion getSurveyQuestionTest() {
		return new SurveyQuestion();
	}

	@Override
	public SurveyList getSurveyListTest() {

		return new SurveyList();
	}

	@Override
	public Answer getAnswerTest() {
		return new Answer();
	}

	@Override
	public Group getGroupTest() {
		return new Group();
	}

	@Override
	public SurveyGroups getSurveyGroupsTest() {
		return new SurveyGroups();
	}
}
