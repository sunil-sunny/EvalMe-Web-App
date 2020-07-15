package com.group18.asdc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.group18.asdc.GroupFormationConfig;
import com.group18.asdc.ProfileManagementConfig;
import com.group18.asdc.dao.GroupFormationDao;
import com.group18.asdc.entities.Answer;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.Option;
import com.group18.asdc.entities.SurveyGroups;
import com.group18.asdc.entities.SurveyMetaData;
import com.group18.asdc.entities.SurveyQuestion;
import com.group18.asdc.entities.User;
import com.group18.asdc.groupformation.ComputeDistance;
import com.group18.asdc.groupformation.GroupFormation;
import com.group18.asdc.groupformation.GroupFormationAdapter;
import com.group18.asdc.groupformation.GroupFormationAdapterImpl;
import com.group18.asdc.groupformation.IComputeDistance;
import com.group18.asdc.groupformation.IGroupFormation;
import com.group18.asdc.util.IQueryVariableToArrayList;

public class GroupFormationServiceImpl implements GroupFormationService {

	private Logger logger = Logger.getLogger(GroupFormationService.class.getName());

	@Override
	public SurveyGroups getGroupFormationResults(Course course) {
		GroupFormationDao theGroupFormationDao = GroupFormationConfig.getSingletonInstance().getTheGroupFormationDao();
		return theGroupFormationDao.getGroupFormationResults(course);
	}

	public HashMap formGroupsForSurvey(Course course, SurveyAnswersService surveyAnswersService,
			SurveyService surveyService, IQueryVariableToArrayList queryVariableToArraylist) {
		HashMap<String, Object> resultMap = new HashMap<>();
		SurveyMetaData surveyQuestionData = surveyService.getSavedSurvey(course);
		ArrayList<Answer> answerList = surveyAnswersService.fetchAnswersForSurvey(surveyQuestionData.getSurveyId(),
				queryVariableToArraylist);
		GroupFormationAdapter adapter = new GroupFormationAdapterImpl(surveyQuestionData, answerList);
		ArrayList questionList = adapter.getQuestionList();
		ArrayList userList = adapter.getUserList();
		ArrayList userAnswersList = adapter.getUserAnswersList();
		IComputeDistance computeDistance = new ComputeDistance(userAnswersList, questionList);
		IGroupFormation groupFormation = new GroupFormation(computeDistance.compute(), userList,
				surveyQuestionData.getGroupSize());
		resultMap.put("userDataMap", fetchGroupDetails(userList, surveyQuestionData, answerList));
		resultMap.put("groupList", groupFormation.formGroups());
		return resultMap;
	}

	@Override
	public HashMap<String, HashMap> fetchGroupDetails(ArrayList<String> userIdList, SurveyMetaData surveyMetaData,
			ArrayList<Answer> answerList) {
		logger.log(Level.INFO,
				"Fetch user information for displaying survey results userList=" + userIdList.toString());
		HashMap<String, HashMap> userSurveyMap = new HashMap<>();
		UserService userService = ProfileManagementConfig.getSingletonInstance().getTheUserService();
		User user = null;
		HashMap userMap = null;
		ArrayList<SurveyQuestion> surveyQuestionList = (ArrayList) surveyMetaData.getSurveyQuestions();
		for (String bannerId : userIdList) {
			userMap = new HashMap<>();
			user = new User();
			userService.loadUserWithBannerId(bannerId, user);
			if (user.isValidUser()) {
				userMap.put("FIRST_NAME", user.getFirstName());
				userMap.put("LAST_NAME", user.getLastName());
				ArrayList userAnswerList = new ArrayList<>();
				for (SurveyQuestion eachSurveyQuestion : surveyQuestionList) {
					for (Answer eachAnswer : answerList) {
						if (eachAnswer.getBannerId().equals(bannerId)
								&& eachAnswer.getSurveyQuestionId().equals(eachSurveyQuestion.getSurveyQuestionId())) {
							if (eachSurveyQuestion.getOptions().size() > 0) {
								String[] answersOption = eachAnswer.getAnswers().split(",");
								for (String eachOption : answersOption) {
									ArrayList<Option> optionsList = (ArrayList) eachSurveyQuestion.getOptions();
									for (Option eachOptionInQuestion : optionsList) {
										if (((Integer) eachOptionInQuestion.getStoredData())
												.equals(Integer.valueOf(eachOption))) {
											userAnswerList.add(eachOptionInQuestion.getDisplayText());
										}
									}
								}
							} else {
								userAnswerList.add(eachAnswer.getAnswers());
							}

						}
					}
				}
				userMap.put("ANSWERS", userAnswerList);
			}
			userSurveyMap.put(bannerId, userMap);
		}
		logger.log(Level.INFO, "Fetched user details for group formation" + userSurveyMap.toString());
		return userSurveyMap;
	}

}