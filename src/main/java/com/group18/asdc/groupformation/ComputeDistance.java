package com.group18.asdc.groupformation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.group18.asdc.entities.LogicDetail;
import com.group18.asdc.entities.QuestionType;

public class ComputeDistance {

    private ArrayList<HashMap> userAnswerList = null;
    private ArrayList<HashMap> questionsList = null;
    private Float[][][] distanceMatrix = null;

    private Logger logger = Logger.getLogger(ComputeDistance.class.getName());

    public ComputeDistance(ArrayList<HashMap> userAnswerList, ArrayList<HashMap> questionsList) {
        this.userAnswerList = userAnswerList;
        this.questionsList = questionsList;
        this.distanceMatrix = new Float[userAnswerList.size()][userAnswerList.size()][questionsList.size()];
        //
        logger.log(Level.INFO, "User answer list" + userAnswerList.toString());
        logger.log(Level.INFO, "Questions list" + questionsList.toString());
    }

    public void compute() {
        int questionsIterator = 0;
        for (HashMap eachQuestionMap : questionsList) {
            Integer questionId = (Integer) eachQuestionMap.get("QUESTION_ID");
            String questionType = (String) eachQuestionMap.get("QUESTION_TYPE");
            String questionLogic = (String) eachQuestionMap.get("QUESTION_LOGIC");
            int numberOfOptions = (Integer) eachQuestionMap.get("QUESTION_OPTIONS");
            int currentUserIterator = 0;
            for (HashMap currentUserMap : userAnswerList) {
                ArrayList currentUserAnswer = (ArrayList) currentUserMap.get(questionId);
                //
                int otherUserIterator = 0;
                for (HashMap otherUserMap : userAnswerList) {
                    ArrayList otherUserAnswer = (ArrayList) otherUserMap.get(questionId);
                    Float distance = 0.0f;
                    //
                    if (questionType.equals(QuestionType.MULTIPLE_CHOOSE_ONE.toString())) {
                        if (currentUserAnswer.get(0).equals(otherUserAnswer.get(0))) {
                            distance = 0.0f;
                        } else {
                            distance = 1.0f;
                        }
                        //
                        if (questionLogic.equals(LogicDetail.Group_Disimilar.toString())) {
                            distance = 1.0f - distance;
                        }
                    } else if (questionType.equals(QuestionType.MULTIPLE_CHOOSE_MORE.toString())) {
                        //
                        int optionsMatched = 0;
                        for (Object eachOption : currentUserAnswer) {
                            eachOption = (Integer) eachOption;
                            //
                            if (otherUserAnswer.contains(eachOption)) {
                                optionsMatched++;
                            }
                        }
                        //
                        int totalItems = currentUserAnswer.size() + otherUserAnswer.size();
                        int unmatchedItems = totalItems - (2 * optionsMatched);
                        //
                        Float unmatchedNormalized = (float) unmatchedItems / (float) numberOfOptions;
                        //
                        if (questionLogic.equals(LogicDetail.Group_Similar.toString())) {
                            distance = unmatchedNormalized;
                        } else {
                            distance = 1.0f - unmatchedNormalized;
                        }

                    } else if (questionType.equals(QuestionType.NUMERIC_TYPE.toString())) {
                        distance = Math.abs((Integer) currentUserAnswer.get(0) - (Integer) otherUserAnswer.get(0))
                                / 100.0f;
                        //
                        if (questionLogic.equals(LogicDetail.Group_Disimilar.toString())) {
                            distance = 1.0f - distance;
                        }
                    }
                    //
                    distanceMatrix[currentUserIterator][otherUserIterator][questionsIterator] = distance;
                    otherUserIterator++;
                }

                currentUserIterator++;
            }
            questionsIterator++;
        }
        //
        logger.log(Level.INFO, "Computed distance list" + Arrays.deepToString(distanceMatrix));
    }

}