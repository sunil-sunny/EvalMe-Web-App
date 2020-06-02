package com.group18.asdc.service;

import java.util.List;

import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;

public interface CourseDetailsService {
	
	public List<Course> getAllCourses();
	public boolean allocateTa(String courseId,String bannerId);
	public boolean isUserExists(User user);
	public User getUserById(String bannerId);
	public boolean enrollStuentsIntoCourse(List<User> studentList,String courseId);
	public void registerStudents(List<User> studentList);
	public List<User> filterEligibleStudentsForCourse(List<User> studentList,String courseId);

}
