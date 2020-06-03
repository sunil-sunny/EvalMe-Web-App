package com.group18.asdc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.CourseAdmin;

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
	public String adminAddForm(@ModelAttribute CourseAdmin courseadmin) {
		
		return "adminaddcourseresult";
	}
	
	//DELETE COURSE 
	
	@GetMapping("/admindelete")
	public String adminDeleteDisplay(Model model) {
		model.addAttribute("course", new Course());
		return "admindeletecourse";
	}

	@PostMapping("/admindelete")
	public String adminDeleteForm(@ModelAttribute Course course) {
		return "admindeletecourseresult";
	}
}

