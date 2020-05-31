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
		return "studentcoursehome";
	}

	@RequestMapping(value = "/coursepageInstrcutor", method = RequestMethod.GET)
	public String getCoursePageForInstrcutorOrTA(Model theModel, HttpServletRequest request) {

		String courseId = request.getParameter("id");
		String courseName = request.getParameter("name");
		theModel.addAttribute("courseId", courseId);
		theModel.addAttribute("coursename", courseName);
		//System.out.println("in controller");
		return "instrcutorcoursehome";
	}
	@RequestMapping(value = "/enrolledcourses")
	public String getEnrolledCourses(Model theModel) {

		List<Course> coursesList = courseDetailsService.getAllCourses();
		theModel.addAttribute("coursesList", coursesList);
		return "enrolledcourses";
	}

	@RequestMapping(value = "/allocateTA", method = RequestMethod.POST)
	public String allocateTA(HttpServletRequest request,Model theModel) {

		String bannerId = request.getParameter("TA");
		String courseId=request.getParameter("courseid");
		String courseName=request.getParameter("coursename");
		theModel.addAttribute("courseId", courseId);
		theModel.addAttribute("coursename", courseName);
		boolean userExists = courseDetailsService.isUserExists(bannerId);
		if (userExists) {
			courseDetailsService.allocateTa(courseId, bannerId);
			theModel.addAttribute("result", "TA Allocated");
			return "instrcutorcoursehome";
		} else {

			theModel.addAttribute("result", "user not exists");
			return "instrcutorcoursehome";
		}

	}
	
	@RequestMapping(value = "/instructedcourses",method= RequestMethod.GET)
	public String getInstructedCourses(Model theModel) {
		List<Course> coursesList = courseDetailsService.getAllCourses();
		theModel.addAttribute("coursesList", coursesList);
		return "teachingcourses";
	}
	
	@RequestMapping(value="/uploadstudents", method= RequestMethod.POST)
	public String uploadStudentsToCourse() {
		System.out.println("in uploaad");
		return "home";
	}

}
