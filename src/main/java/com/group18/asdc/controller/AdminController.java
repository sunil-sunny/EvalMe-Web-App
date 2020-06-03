package com.group18.asdc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	
	@GetMapping("/adminhome")
	public String addOrDeleteCourse() {
		return "adminhome";
	}
	

}
