package com.group18.asdc.service;

import java.util.List;

import com.group18.asdc.entities.Course;

public interface CourseDetailsService {
	
	public List<Course> getAllCourses();
	public boolean allocateTa(String courseId,String bannerId);
	public boolean isUserExists(String bannerId);

}
