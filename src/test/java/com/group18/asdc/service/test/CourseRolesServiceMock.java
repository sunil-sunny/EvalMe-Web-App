package com.group18.asdc.service.test;

import java.util.List;

import com.group18.asdc.entities.User;
import com.group18.asdc.service.CourseRolesService;

public class CourseRolesServiceMock implements CourseRolesService {

	@Override
	public boolean allocateTa(int courseId, User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean enrollStuentsIntoCourse(List<User> studentList, int courseId) {
		// TODO Auto-generated method stub
		return false;
	}

}
