package com.group18.asdc.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group18.asdc.dao.UserDao;
import com.group18.asdc.entities.User;
import com.group18.asdc.service.UserService;
import com.group18.asdc.util.CommonUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Boolean authenticateByEmailAndPassword(String email, String password) {
        ArrayList<Object> valuesList = CommonUtil.getInstance().convertQueryVariablesToArrayList(email, password);
        try {
            return userDao.authenticateByEmailAndPassword(valuesList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //
        return Boolean.FALSE;
    }
    
    @Override
	public boolean isUserExists(User user) {
		// TODO Auto-generated method stub
		return userDao.isUserExists(user);
	}
    
    @Override
	public User getUserById(String bannerId) {
		
		return userDao.getUserById(bannerId);
	}
    
    @Override
	public List<User> filterEligibleUsersForCourse(List<User> studentList, int courseId) {
		
		
		return userDao.filterEligibleUsersForCourse(studentList, courseId);
	}

	@Override
	public List<User> getStudentsByCourse(int courseId) {
		// TODO Auto-generated method stub
		return userDao.getStudentsByCourse(courseId);
	}

    @Override
    public void loadUserWithBannerId(String bannerId, User userObj) {
        ArrayList<Object> valuesList = CommonUtil.getInstance().convertQueryVariablesToArrayList(bannerId);
        try {
            userDao.loadUserWithBannerId(valuesList, userObj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean updatePassword(User userObj) {
        ArrayList<Object> criteriaList = CommonUtil.getInstance().convertQueryVariablesToArrayList(userObj.getBannerId());
        ArrayList<Object> valueList = CommonUtil.getInstance().convertQueryVariablesToArrayList(userObj.getPassword());
        try {
            return userDao.updatePassword(criteriaList, valueList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }

}