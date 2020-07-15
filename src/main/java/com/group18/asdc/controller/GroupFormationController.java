package com.group18.asdc.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.group18.asdc.CourseConfig;
import com.group18.asdc.GroupFormationConfig;
import com.group18.asdc.ProfileManagementConfig;
import com.group18.asdc.SurveyConfig;
import com.group18.asdc.entities.Answer;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.SurveyGroups;
import com.group18.asdc.entities.SurveyMetaData;
import com.group18.asdc.groupformation.GroupFormationAdapter;
import com.group18.asdc.groupformation.GroupFormationAdapterImpl;
import com.group18.asdc.service.CourseDetailsService;
import com.group18.asdc.service.GroupFormationService;
import com.group18.asdc.service.SurveyAnswersService;

@Controller
@RequestMapping("/groupformation")
public class GroupFormationController {

	@RequestMapping(value = "/result", method = RequestMethod.GET)
	public String getSurveyPage(Model theModel, HttpServletRequest request) {

		String courseid = request.getParameter("courseId");
		int courseId = Integer.parseInt(courseid);

		Course course = new Course();
		CourseDetailsService theCourseDetailsService = CourseConfig.getSingletonInstance().getTheCourseDetailsService();
		course = theCourseDetailsService.getCourseById(courseId);
		String courseName = course.getCourseName();

		theModel.addAttribute("courseid", courseId);
		theModel.addAttribute("coursename", courseName);

		SurveyMetaData surveyQuestionData = SurveyConfig.getSingletonInstance().getTheSurveyService()
				.getSavedSurvey(course);
		SurveyAnswersService surveyAnswersService = SurveyConfig.getSingletonInstance().getSurveyAnswersService();
		ArrayList<Answer> answerList = surveyAnswersService.fetchAnswersForSurvey(surveyQuestionData.getSurveyId(),
				ProfileManagementConfig.getSingletonInstance().getQueryVariableToArrayList());

		GroupFormationAdapter adapter = new GroupFormationAdapterImpl(surveyQuestionData , answerList);
				
		// System.out.println("Question list" + adapter.getQuestionList().toString());
		// System.out.println("Answer list" + adapter.getUserAnswersList().toString());
		// System.out.println("User list" + adapter.getUserList().toString());
		



		GroupFormationService theGroupFormationService = GroupFormationConfig.getSingletonInstance()
				.getTheGroupFormationService();
		SurveyGroups theSurveyGroups = new SurveyGroups();
		theSurveyGroups = theGroupFormationService.getGroupFormationResults(course);
		theModel.addAttribute("survey", theSurveyGroups);
		return "groupformationresult";
	}
}