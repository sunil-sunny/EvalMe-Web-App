package com.group18.asdc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group18.asdc.dao.CourseDetailsDao;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.Registerbean;
import com.group18.asdc.entities.User;
import com.group18.asdc.util.GroupFormationToolUtil;

@Service
public class CourseDetailsServiceImpl implements CourseDetailsService {

	@Autowired
	private CourseDetailsDao courseDetailsDao;

	@Autowired
	private Registerservice Registerservice;

	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;

	@Override
	public List<Course> getAllCourses() {

		return courseDetailsDao.getAllCourses();
	}

	@Override
	public boolean allocateTa(int courseId, User user) {

		// inserting user in a list since the filter users methods takes the arraylist
		// as input
		List<User> taAsList = new ArrayList<User>();
		//User user = userService.getUserById(bannerId);
		List<User> eligibleUser = null;

		if (user != null) {
			taAsList.add(user);
			eligibleUser = userService.filterEligibleUsersForCourse(taAsList, courseId);
		}

		if (eligibleUser != null && eligibleUser.size() != 0) {

			return courseDetailsDao.allocateTa(courseId, user);
		}

		return false;
	}

	@Override
	public boolean enrollStuentsIntoCourse(List<User> studentList, int courseId) {

		this.registerStudents(studentList);
		List<User> eligibleStudents = userService.filterEligibleUsersForCourse(studentList, courseId);

		return courseDetailsDao.enrollStudentsIntoCourse(eligibleStudents, courseId);
	}

	@Override
	public void registerStudents(List<User> studentList) {

		for (User user : studentList) {

			if (!userService.isUserExists(user)) {

				String result = Registerservice.registeruser(new Registerbean(user));

				if (result.equalsIgnoreCase("success")) {
					String messageText="Thank you for being a part of us !! \n  you username is "+user.getBannerId()+" and the password is "+
					user.getBannerId().concat(GroupFormationToolUtil.passwordTag);
					emailService.sendSimpleMessage(user.getEmail(), "you are now a part of EvalMe", messageText);
				} else {
					System.out.println("user registartion error");
				}
			}
		}
	}

	@Override
	public List<Course> getCoursesWhereUserIsStudent(User user) {

		return courseDetailsDao.getCoursesWhereUserIsStudent(user);
	}

	@Override
	public List<Course> getCoursesWhereUserIsInstrcutor(User user) {

		return courseDetailsDao.getCoursesWhereUserIsInstrcutor(user);
	}

	@Override
	public List<Course> getCoursesWhereUserIsTA(User user) {

		return courseDetailsDao.getCoursesWhereUserIsTA(user);
	}

}
