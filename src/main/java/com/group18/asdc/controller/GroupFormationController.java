package com.group18.asdc.controller;

import javax.servlet.http.HttpServletRequest;

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
		theModel.addAttribute("courseid",courseid);
		return "groupformationresult";
	}
}
