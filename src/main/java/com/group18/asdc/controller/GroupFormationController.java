package com.group18.asdc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.group18.asdc.CourseConfig;
import com.group18.asdc.entities.Course;
import com.group18.asdc.service.CourseDetailsService;

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
		
		theModel.addAttribute("courseid",courseId);
		theModel.addAttribute("coursename",courseName);
		return "groupformationresult";
	}
}