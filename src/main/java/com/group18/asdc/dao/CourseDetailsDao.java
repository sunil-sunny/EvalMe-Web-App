package com.group18.asdc.dao;

import java.util.List;

import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;

public interface CourseDetailsDao {
	
	public List<Course> getAllCourses();
	public boolean allocateTa(String courseId,String bannerId);
	
	public List<Course> getCourseWhereUserIsInstrcutor(String bannerid);
	//public List<Course> getCourseWhereUserIsTA(String bannerid);
	public List<User> filterEligibleStudentsForCourse(List<User> studentList,String courseId);
	public List<User> getStudentsByCourse(String courseId);
	public boolean enrollStudentsIntoCourse(List<User> studentList,String courseId);

}
