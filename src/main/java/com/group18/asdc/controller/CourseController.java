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

	/*
	 * user home end point directs all the user except admin to the page 
	 * where list of all courses are present
	 */
	@GetMapping("/userhome")
	public String getHomePage(Model theModel) {

		List<Course> coursesList = courseDetailsService.getAllCourses();
		theModel.addAttribute("coursesList", coursesList);

		return "userhome";
	}

	/*
	 * courpage redirects user to page which contain courses where he/she is enrolled
	 * as student
	 */

	@RequestMapping(value = "/enrolledcourses")
	public String getEnrolledCourses(Model theModel) {

		List<Course> coursesList = courseDetailsService.getAllCourses();
		theModel.addAttribute("coursesList", coursesList);
		return "enrolledcourses";
	}
	
	
	/*
	 * Below endpoint redirects users to page which contains courses where he/she is having a role as TA.
	 */
	
	@GetMapping("/tacourses")
	public String getTACourses(Model theModel) {

		List<Course> coursesList = courseDetailsService.getAllCourses();
		theModel.addAttribute("coursesList", coursesList);

		return "tacourses";
	}
	
	/*
	 *  Below endpoint redirects users to page which contains courses where he/she is having a role as Instructor
	 */
	
	@RequestMapping(value = "/instructedcourses",method= RequestMethod.GET)
	public String getInstructedCourses(Model theModel) {
		List<Course> coursesList = courseDetailsService.getAllCourses();
		theModel.addAttribute("coursesList", coursesList);
		return "teachingcourses";
	}
	
	/*
	 * Below endpoint refers student version of course home page.
	 */
	
	@RequestMapping(value = "/coursepage", method = RequestMethod.GET)
	public String getCoursePage(Model theModel, HttpServletRequest request) {

		String courseId = request.getParameter("id");
		String courseName = request.getParameter("name");
		theModel.addAttribute("courseId", courseId);
		theModel.addAttribute("coursename", courseName);
		return "studentcoursehome";
	}

	/*
	 * Below endpoint refers TA or instructor version of course home page.
	 */
	@RequestMapping(value = "/coursepageInstrcutor", method = RequestMethod.GET)
	public String getCoursePageForInstrcutorOrTA(Model theModel, HttpServletRequest request) {

		String courseId = request.getParameter("id");
		String courseName = request.getParameter("name");
		theModel.addAttribute("courseId", courseId);
		theModel.addAttribute("coursename", courseName);
		return "instrcutorcoursehome";
	}

	
	/*
	 * Below endpoint allocates particulat user as TA
	 */
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
	
	
	
	/*
	 * Below endpoint uploads the student csv file and enroll them in particular course
	 */
	
	@RequestMapping(value="/uploadstudents", method= RequestMethod.POST)
	public String uploadStudentsToCourse() {
		System.out.println("in uploaad");
		return "home";
	}

	
}
