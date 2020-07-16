package com.group18.asdc.entities;

public interface ModelAbstractFactory {

	public BasicQuestionData getBasicQuestionData();

	public Option getOption();

	public Course getCourse();

	public MultipleChoiceQuestion getMultipleChoiceQuestion();

	public PasswordHistory getPasswordHistory();

	public QuestionMetaData getQuestionMetaData();

	public SurveyMetaData getSurveyMetaData();

	public UserRegistartionDetails getIUserRegistartionDetails();

	public User getUser();

	public SurveyQuestion getSurveyQuestion();

	public SurveyList getSurveyList();
	
	public SurveyGroups getSurveyGroups();
	
	public Group getGroup();

}
