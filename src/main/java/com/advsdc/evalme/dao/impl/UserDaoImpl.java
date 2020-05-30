package com.advsdc.evalme.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import com.advsdc.evalme.dao.UserDao;
import com.advsdc.evalme.dbconnection.SQLMethods;
import com.advsdc.evalme.dbconnection.SQLQueries;

import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoImpl implements UserDao {

    @Autowired
    private SQLMethods sqlImplementation;

    @Override
    public Boolean authenticateByEmailAndPassword(ArrayList<Object> valuesList) throws SQLException {
        return sqlImplementation.selectQuery(SQLQueries.USER_AUTH_BY_EMAIL_PASSWORD.toString(), valuesList).size() == 1;
    }

}