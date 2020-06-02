package com.group18.asdc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group18.asdc.dao.CourseDetailsDao;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.Registerbean;
import com.group18.asdc.entities.User;

@Service
public class CourseDetailsServiceImpl implements CourseDetailsService {
	
	@Autowired
	private CourseDetailsDao courseDetailsDao;
	
	@Autowired
	private Registerservice Registerservice;
	
	@Autowired
	private UserService userService;

	@Override
	public List<Course> getAllCourses() {
		
		return courseDetailsDao.getAllCourses();
	}

	@Override
	public boolean allocateTa(String courseId, String bannerId) {
		
		return courseDetailsDao.allocateTa(courseId, bannerId);
	}

	

	@Override
	public boolean enrollStuentsIntoCourse(List<User> studentList,String courseId) {
	
		this.registerStudents(studentList);
		List<User> eligibleStudents=this.filterEligibleStudentsForCourse(studentList, courseId);
		
		return courseDetailsDao.enrollStudentsIntoCourse(eligibleStudents, courseId);
	}

	
	@Override
	public void registerStudents(List<User> studentList) {
		
	
		for(User user:studentList) {
			
			if(!userService.isUserExists(user)) {
	
				String result=Registerservice.registeruser(new Registerbean(user));
				
				if(result.equalsIgnoreCase("success")) {
					//trigger email
				}
				else {
					System.out.println("user registartion error");
				}
			}
		}
	}

	

	@Override
	public List<User> filterEligibleStudentsForCourse(List<User> studentList, String courseId) {
		
		
		return courseDetailsDao.filterEligibleStudentsForCourse(studentList, courseId);
	}

	

}
