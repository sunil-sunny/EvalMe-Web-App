package com.group18.asdc.dao;

import java.util.List;

import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;

public interface CourseDetailsDao {
	
	public List<Course> getAllCourses();
	public boolean allocateTa(String courseId,String bannerId);
	public boolean isUserExists(String bannerId);
	public User getUserById(String bannerId);

}
