package com.group18.asdc.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.group18.asdc.CourseConfig;
import com.group18.asdc.ProfileManagementConfig;
import com.group18.asdc.entities.User;
import com.group18.asdc.service.CourseRolesService;
import com.group18.asdc.service.UserService;
import com.group18.asdc.util.ConstantStringUtil;

@Controller
public class CourseRolesController {

	@RequestMapping(value = "/allocateTA", method = RequestMethod.POST)
	public String allocateTA(HttpServletRequest request, Model theModel) {
		String bannerId = request.getParameter("TA");
		String courseId = request.getParameter("courseid");
		String courseName = request.getParameter("coursename");
		theModel.addAttribute("courseId", courseId);
		theModel.addAttribute("coursename", courseName);
		UserService userService = ProfileManagementConfig.getSingletonInstance().getTheUserService();
		CourseRolesService courseRolesService = CourseConfig.getSingletonInstance().getTheCourseRolesService();
		User user = userService.getUserById(bannerId);
		if (null == user) {
			theModel.addAttribute("result", "User doesnt exists or given id is invalid");
			return "instrcutorcoursehome";
		} else {
			boolean isAllocated = courseRolesService.allocateTa(Integer.parseInt(courseId),
					userService.getUserById(bannerId));
			if (isAllocated) {
				theModel.addAttribute("result", "TA Allocated");
			} else {
				theModel.addAttribute("result", "User is already a part of this course");
			}
			return "instrcutorcoursehome";
		}
	}

	@RequestMapping(value = "/uploadstudents", method = RequestMethod.POST)
	public String uploadStudentsToCourse(@RequestParam(name = "file") MultipartFile file, Model theModel,
			HttpServletRequest request) {
		String courseId = request.getParameter("courseid");
		String courseName = request.getParameter("coursename");
		theModel.addAttribute("courseId", courseId);
		theModel.addAttribute("coursename", courseName);
		CourseRolesService courseRolesService = CourseConfig.getSingletonInstance().getTheCourseRolesService();
		if (0 == courseId.length()) {
			theModel.addAttribute("resultEnrolling", "Error in loading file !! please try again");
		} else {
			if (file.isEmpty()) {
				theModel.addAttribute("resultEnrolling", "Upload file to continue");
			} else {
				try {
					byte[] bytes = file.getBytes();
					ByteArrayInputStream inputFilestream = new ByteArrayInputStream(bytes);
					BufferedReader br = new BufferedReader(new InputStreamReader(inputFilestream));
					String line = "";
					br.readLine();
					List<User> validUsers = new ArrayList<User>();
					List<User> inValidUsers = new ArrayList<User>();
					User user = null;
					while ((line = br.readLine()) != null) {
						user = new User();
						String userDetails[] = line.split(",");
						if (userDetails.length == 4) {
							String firstName = userDetails[0];
							String lastName = userDetails[1];
							String bannerId = userDetails[2];
							String email = userDetails[3];

							if (bannerId.matches(ConstantStringUtil.BANNER_ID_CHECK.toString())
									|| bannerId.length() == 9
									|| email.matches(ConstantStringUtil.EMAIL_PATTERN_CHECK.toString())) {
								user.setFirstName(firstName);
								user.setLastName(lastName);
								user.setBannerId(bannerId);
								user.setEmail(email);
								validUsers.add(user);
							} else {
								inValidUsers.add(user);
							}
						} else {
							theModel.addAttribute("fileDetailsErrors", "Rows which has invalid details are ignored");
						}
					}
					if (validUsers.size() > 0) {
						boolean status = courseRolesService.enrollStuentsIntoCourse(validUsers,
								Integer.parseInt(courseId));
						if (status) {
							theModel.addAttribute("resultEnrolling", "All Students enrolled");
						} else {
							theModel.addAttribute("resultEnrolling",
									"Success!! " + "Users who are already related to course are ignored");
						}
					}
					br.close();
				} catch (IOException e) {
					theModel.addAttribute("resultEnrolling", "File is not readable, Kinldy upload it again");
				}
			}
		}
		return "instrcutorcoursehome";
	}
}