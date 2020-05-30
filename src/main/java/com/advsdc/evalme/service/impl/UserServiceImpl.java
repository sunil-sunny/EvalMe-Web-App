package com.advsdc.evalme.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import com.advsdc.evalme.dao.UserDao;
import com.advsdc.evalme.service.UserService;
import com.advsdc.evalme.util.CommonUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

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

}