package com.group18.asdc.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.group18.asdc.SurveyConfig;
import com.group18.asdc.dao.SurveyAnswerDao;
import com.group18.asdc.dao.SurveyAnswerDaoImpl;
import com.group18.asdc.entities.Answer;
import com.group18.asdc.util.IQueryVariableToArrayList;

public class SurveyAnswerServiceImpl implements SurveyAnswersService {

    @Override
    public ArrayList fetchAnswersForSurvey(int surveyId, IQueryVariableToArrayList queryVariableToArrayList) {
        ArrayList<Answer> answersList = new ArrayList();
        SurveyAnswerDao surveyAnswerDao = SurveyConfig.getSingletonInstance().getSurveyAnswerDao();
        ArrayList<Object> valuesList = queryVariableToArrayList.convertQueryVariablesToArrayList(surveyId);
        ArrayList<HashMap> resultList = surveyAnswerDao.fetchAnswersForSurvey(valuesList);
        for (HashMap eachAnswer : resultList) {
            Answer answer = new Answer((String) eachAnswer.get("answer"), (String) eachAnswer.get("bannerid"),
                    (Integer) eachAnswer.get("surveyquestionid"));
            answersList.add(answer);
        }
        return answersList;
    }

}