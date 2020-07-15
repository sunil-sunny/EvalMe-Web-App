package com.group18.asdc.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.group18.asdc.CourseConfig;
import com.group18.asdc.GroupFormationConfig;
import com.group18.asdc.ProfileManagementConfig;
import com.group18.asdc.SurveyConfig;
import com.group18.asdc.entities.Course;
import com.group18.asdc.service.CourseDetailsService;
import com.group18.asdc.service.GroupFormationService;
import com.group18.asdc.service.SurveyAnswersService;
import com.group18.asdc.service.SurveyService;
import com.group18.asdc.util.IQueryVariableToArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		GroupFormationService groupFormationService = GroupFormationConfig.getSingletonInstance()
				.getTheGroupFormationService();
		SurveyAnswersService surveyAnswersService = SurveyConfig.getSingletonInstance().getSurveyAnswersService();
		SurveyService surveyService = SurveyConfig.getSingletonInstance().getTheSurveyService();
		IQueryVariableToArrayList queryVariableToArraylist = ProfileManagementConfig.getSingletonInstance()
				.getQueryVariableToArrayList();
		HashMap resultMap = groupFormationService.formGroupsForSurvey(course, surveyAnswersService, surveyService,
				queryVariableToArraylist);
		theModel.addAttribute("userDataMap", resultMap.get("userDataMap"));
		theModel.addAttribute("groupList", resultMap.get("groupList"));
		return "groupformationresult";
	}
}