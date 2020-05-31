package com.group18.asdc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group18.asdc.dao.CourseDetailsDao;
import com.group18.asdc.entities.Course;

@Service
public class CourseDetailsServiceImpl implements CourseDetailsService {
	
	@Autowired
	private CourseDetailsDao courseDetailsDao;

	@Override
	public List<Course> getAllCourses() {
		
		return courseDetailsDao.getAllCourses();
	}

	@Override
	public boolean allocateTa(String courseId, String bannerId) {
		
		return courseDetailsDao.allocateTa(courseId, bannerId);
	}

	@Override
	public boolean isUserExists(String bannerId) {
		// TODO Auto-generated method stub
		return courseDetailsDao.isUserExists(bannerId);
	}

}
