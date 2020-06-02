package com.group18.asdc.service;

import java.util.List;

import com.group18.asdc.entities.User;

public interface UserService {

	Boolean authenticateByEmailAndPassword(String email, String password);

	public boolean isUserExists(User user);

	public User getUserById(String bannerId);

	public List<User> filterEligibleUsersForCourse(List<User> studentList, int courseId);

	public List<User> getStudentsByCourse(int courseId);
	
}