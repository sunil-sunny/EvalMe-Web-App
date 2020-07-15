package com.group18.asdc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.dao.UserDao;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;
import com.group18.asdc.security.IPasswordEncryption;
import com.group18.asdc.util.IQueryVariableToArrayList;

public class UserServiceImpl implements UserService {

	private static final UserDao theUserDao = SystemConfig.getSingletonInstance().getDaoAbstractFactory().getUserDao();
	private IQueryVariableToArrayList queryVariableToArrayList;

	public UserServiceImpl(IQueryVariableToArrayList queryVariableToArrayList) {
		super();
		this.queryVariableToArrayList = queryVariableToArrayList;
	}

	public UserServiceImpl() {
		super();
	}

	@Override
	public boolean isUserExists(User user) {
		return theUserDao.isUserExists(user);
	}

	@Override
	public User getUserById(String bannerId) {
		return theUserDao.getUserById(bannerId);
	}

	@Override
	public List<User> getAllUsersByCourse(int courseId) {
		return theUserDao.getAllUsersByCourse(courseId);
	}

	@Override
	public int loadUserWithBannerId(String bannerId, User userObj) {
		ArrayList<Object> valuesList = queryVariableToArrayList.convertQueryVariablesToArrayList(bannerId);
		return theUserDao.loadUserWithBannerId(valuesList, userObj);
	}

	@Override
	public Boolean updatePassword(User userObj, IPasswordEncryption passwordEncryption) {
		ArrayList<Object> criteriaList = queryVariableToArrayList
				.convertQueryVariablesToArrayList(userObj.getBannerId());
		ArrayList<Object> valueList = queryVariableToArrayList
				.convertQueryVariablesToArrayList(passwordEncryption.encryptPassword(userObj.getPassword()));
		return theUserDao.updatePassword(criteriaList, valueList);
	}

	@Override
	public ArrayList getUserRoles(User userObj) {
		ArrayList<Object> criteriaList = queryVariableToArrayList
				.convertQueryVariablesToArrayList(userObj.getBannerId());
		return theUserDao.getUserRoles(criteriaList);
	}

	@Override
	public User getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String bannerid = "";
		if (principal instanceof UserDetails) {
			bannerid = ((UserDetails) principal).getUsername();
		} else {
			bannerid = principal.toString();
		}
		User currentUser = null;
		if (null != bannerid) {
			currentUser = this.getUserById(bannerid);
		}
		return currentUser;
	}

	@Override
	public boolean isUserInstructor(Course course) {

		return theUserDao.isUserInstructor(course);
	}
}