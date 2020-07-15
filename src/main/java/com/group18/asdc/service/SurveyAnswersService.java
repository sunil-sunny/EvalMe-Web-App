package com.group18.asdc.service;

import java.util.ArrayList;

import com.group18.asdc.util.IQueryVariableToArrayList;

public interface SurveyAnswersService {

    public ArrayList fetchAnswersForSurvey(int surveyId, IQueryVariableToArrayList queryVariableToArrayList);
    
}