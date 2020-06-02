package com.group18.asdc.service;

import java.sql.SQLException;
import java.util.ArrayList;

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

}