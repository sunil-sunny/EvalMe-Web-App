package com.group18.asdc.service;

import java.util.ArrayList;
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
		
		//inserting user in a list since the filter users methods takes the arraylist as input
		List<User> taAsList=new ArrayList<User>();
		User user=userService.getUserById(bannerId);
		List<User> eligibleUser=null;
		
		if(user!=null) {
			taAsList.add(user);
			eligibleUser=this.filterEligibleUsersForCourse(taAsList, courseId);
		}
		
		if(eligibleUser!=null && eligibleUser.size()!=0) {
			
			return courseDetailsDao.allocateTa(courseId, bannerId);
		}
		
		return false;
	}

	

	@Override
	public boolean enrollStuentsIntoCourse(List<User> studentList,String courseId) {
	
		this.registerStudents(studentList);
		List<User> eligibleStudents=this.filterEligibleUsersForCourse(studentList, courseId);
		
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
	public List<User> filterEligibleUsersForCourse(List<User> studentList, String courseId) {
		
		
		return courseDetailsDao.filterEligibleUsersForCourse(studentList, courseId);
	}

}
