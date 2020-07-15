package com.group18.asdc.groupformation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.group18.asdc.entities.Answer;
import com.group18.asdc.entities.SurveyMetaData;
import com.group18.asdc.entities.SurveyQuestion;

public class GroupFormationAdapterImpl implements GroupFormationAdapter {

    private SurveyMetaData surveyMetaData = null;
    private ArrayList<Answer> answersList = null;
    private ArrayList<HashMap> userAnswerList = null;
    private ArrayList<HashMap> questionList = null;
    private Set<String> usersList = null;

    public GroupFormationAdapterImpl(SurveyMetaData surveyMetaData, ArrayList<Answer> answersList) {
        this.surveyMetaData = surveyMetaData;
        this.answersList = answersList;
        processData();
    }

    private void processData() {
        ArrayList<SurveyQuestion> surveyQuestionList = (ArrayList) surveyMetaData.getSurveyQuestions();
        //
        HashMap<String, Object> questionMap = null;
        questionList = new ArrayList<>();
        for (SurveyQuestion eachSurveyQuestion : surveyQuestionList) {
            questionMap = new HashMap<>();
            //
            questionMap.put("QUESTION_ID", eachSurveyQuestion.getSurveyQuestionId());
            questionMap.put("QUESTION_TYPE",
                    eachSurveyQuestion.getQuestionData().getBasicQuestionData().getQuestionType());
            questionMap.put("QUESTION_LOGIC", eachSurveyQuestion.getLogicDetail());
            questionMap.put("QUESTION_OPTIONS", eachSurveyQuestion.getOptions().size());
            questionMap.put("QUESTION_PRIORITY", eachSurveyQuestion.getPriority());
            //
            questionList.add(questionMap);
        }

        //
        HashMap userAnswerMap = null;
        ArrayList<String> userAnswerOptionsList = null;
        usersList = new HashSet();
        userAnswerList = new ArrayList<>();
        //
        for (Answer eachAnswer : answersList) {
            usersList.add(eachAnswer.getBannerId());
        }
        //
        for (String eachUser : usersList) {
            userAnswerMap = new HashMap<>();
            for (Answer eachAnswer : answersList) {
                if (eachUser.equals(eachAnswer.getBannerId())) {
                    userAnswerOptionsList = new ArrayList<>();
                    //
                    String userAnswers = eachAnswer.getAnswers();
                    userAnswerOptionsList = new ArrayList(Arrays.asList(userAnswers.split(",")));
                    userAnswerMap.put(eachAnswer.getSurveyQuestionId(), userAnswerOptionsList);
                    //

                }
            }
            userAnswerList.add(userAnswerMap);
        }

    }

    @Override
    public ArrayList getQuestionList() {

        return this.questionList;
    }

    @Override
    public ArrayList getUserAnswersList() {

        return this.userAnswerList;
    }

    @Override
    public Set<String> getUserList() {

        return this.usersList;
    }

}