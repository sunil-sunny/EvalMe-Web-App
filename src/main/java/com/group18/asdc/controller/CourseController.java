package com.group18.asdc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.group18.asdc.entities.Course;
import com.group18.asdc.service.CourseDetailsService;

@Controller
public class CourseController {

	@Autowired
	private CourseDetailsService courseDetailsService;

	@GetMapping("/home")
	public String getHomePage(Model theModel) {

		List<Course> coursesList = courseDetailsService.getAllCourses();
		theModel.addAttribute("coursesList", coursesList);

		return "home";
	}

	@RequestMapping(value = "/coursepage", method = RequestMethod.GET)
	public String getCoursePage(Model theModel, HttpServletRequest request) {

		String courseId = request.getParameter("id");
		String courseName = request.getParameter("name");
		theModel.addAttribute("courseId", courseId);
		theModel.addAttribute("coursename", courseName);
		return "student" + "coursehome";
	}

	@RequestMapping(value = "/enrolledcourses")
	public String getEnrolledCourses(Model theModel) {

		List<Course> coursesList = courseDetailsService.getAllCourses();
		theModel.addAttribute("coursesList", coursesList);
		return "enrolledcourses";
	}

	@RequestMapping(value = "/allocateTA", method = RequestMethod.POST)
	public String allocateTA(HttpServletRequest request) {

		String bannerId = request.getParameter("TA");
		boolean userExists = courseDetailsService.isUserExists(bannerId);
		if (userExists) {
			String courseId = request.getParameter("courseid");
			courseDetailsService.allocateTa(courseId, bannerId);
			return "TaSuccess";
		} else {

			return "usernotexists";
		}

	}
	
	@RequestMapping(value = "/instructedcourses")
	public String getInstructedCourses(Model theModel) {
		List<Course> coursesList = courseDetailsService.getAllCourses();
		theModel.addAttribute("coursesList", coursesList);
		return "teachingcourses";
	}

}
