package com.group18.asdc.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.group18.asdc.SystemConfig;
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

	private final static CourseDetailsService theCourseDetailsService = SystemConfig.getSingletonInstance()
			.getServiceAbstractFactory().getCourseDetailsService();
	private final static GroupFormationService theGroupFormationService = SystemConfig.getSingletonInstance()
			.getServiceAbstractFactory().getGroupFormationService();

	@RequestMapping(value = "/result", method = RequestMethod.GET)
	public String getSurveyPage(Model theModel, HttpServletRequest request) {

		String courseid = request.getParameter("courseId");
		int courseId = Integer.parseInt(courseid);

		Course course = new Course();
		course = theCourseDetailsService.getCourseById(courseId);
		String courseName = course.getCourseName();
		theModel.addAttribute("courseid", courseId);
		theModel.addAttribute("coursename", courseName);

		SurveyAnswersService surveyAnswersService = SystemConfig.getSingletonInstance().getServiceAbstractFactory()
				.getSurveyAnswersService();
		SurveyService surveyService = SystemConfig.getSingletonInstance().getServiceAbstractFactory()
				.getSurveyService();
		IQueryVariableToArrayList queryVariableToArraylist = SystemConfig.getSingletonInstance()
				.getUtilAbstractFactory().getQueryVariableToArrayList();
		HashMap<String, Object> resultMap = theGroupFormationService.formGroupsForSurvey(course, surveyAnswersService,
				surveyService, queryVariableToArraylist);

		resultMap.forEach((key, value) -> theModel.addAttribute(key, value));

		return "groupformationresult";
	}
}