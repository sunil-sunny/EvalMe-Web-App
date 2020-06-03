package com.group18.asdc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group18.asdc.entities.User;

public interface UserDao {
    
    Boolean authenticateByEmailAndPassword(ArrayList<Object> valueList) throws SQLException;
    public boolean isUserExists(User user);
	public User getUserById(String bannerId);
	public List<User> filterEligibleUsersForCourse(List<User> studentList,int courseId);
	public List<User> getStudentsByCourse(int courseId);
	public User getInstructorForCourse(int courseId);

    void loadUserWithBannerId(ArrayList<Object> valueList, User userObj)throws SQLException;

    Boolean updatePassword(ArrayList<Object> criteriaList, ArrayList<Object> valuesList)throws SQLException;
}