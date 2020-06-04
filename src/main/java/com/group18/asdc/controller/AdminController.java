package com.group18.asdc.controller;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.CourseAdmin;
import com.group18.asdc.service.AdminServiceImpl;

@Controller
public class AdminController {


	//ADMIN DASHBOARD

	@GetMapping("/adminhome")
	public String adminHome() {
		return "adminhome";
	}

	//ADD COURSE

	@GetMapping("/adminadd")
	public String adminAddDisplay(Model model) {
		model.addAttribute("courseadmin", new CourseAdmin());
		return "adminaddcourse";
	}

	@PostMapping("/adminadd")
	public String adminAddForm(@ModelAttribute("courseadmin") CourseAdmin courseadmin, BindingResult bindingresult) {

		//If any errors occur because of type-mismatch or any other reason, return the same view to add courses
		if(bindingresult.hasErrors()) {
			return "redirect:/adminadd?error";
		}

		//send CourseAdmin object to adminservice for processing user input
		AdminServiceImpl adminservice = new AdminServiceImpl();

		//store the string returned in result, to identify the error and display appropriate alert message
		String result = adminservice.createCourse(courseadmin);

		//check type of error string returned and return view accordingly

		if(result=="invalidid") {
			return "redirect:/adminadd?invalidid";
		}
		else if(result=="shortname") {
			return "redirect:/adminadd?shortname";
		}
		else if(result=="invalidinstid") {
			return "redirect:/adminadd?invalidinstid";
		}
		else if(result=="idexists") {
			return "redirect:/adminaddcourse?idexists";
		}
		else if(result=="nameexists") {
			return "redirect:/adminaddcourse?nameexists";
		}
		else if(result=="invalidinst") {
			return "redirect:/adminaddcourse?invalidinst";
		}
		else if(result=="coursenotcreated") {
			return "redirect:/adminaddcourse?coursenotcreated";
		}
		else {
			return "adminaddcourseresult";
		}
	}

	//DELETE COURSE 

	@GetMapping("/admindelete")
	public String adminDeleteDisplay(Model model) {
		model.addAttribute("course", new Course());
		return "admindeletecourse";
	}

	@PostMapping("/admindelete")
	public String adminDeleteForm(@ModelAttribute("course") Course course, BindingResult bindingresult) {

		//If any errors occur because of type-mismatch or any other reason, return the same view to delete courses
		if(bindingresult.hasErrors()) {
			return "redirect:/admindelete?error";
		}

		//send Course object to adminservice for processing user input
		AdminServiceImpl adminservice = new AdminServiceImpl();

		//store the string returned in result, to identify the error and display appropriate alert message
		String result = adminservice.deleteCourse(course.getCourseId());


		//check type of error string returned and return view accordingly
		if(result=="invalidid") {
			return "redirect:/admindelete?invalidid";
		}
		else if(result=="iddoesnotexist") {
			return "redirect:/adminaddcourse?iddoesnotexist";
		}
		else if(result=="coursenotdeleted") {
			return "redirect:/adminaddcourse?iddoesnotexist";
		}
		else {
			return "admindeletecourseresult";
		}
	}
}

