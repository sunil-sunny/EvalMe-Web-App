package com.group18.asdc.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.group18.asdc.dao.UserDao;
import com.group18.asdc.database.SQLMethods;
import com.group18.asdc.database.SQLQueries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SQLMethods sqlImplementation;

    @Override
    public Boolean authenticateByEmailAndPassword(ArrayList<Object> valuesList) throws SQLException {
        return sqlImplementation.selectQuery(SQLQueries.USER_AUTH_BY_EMAIL_PASSWORD.toString(), valuesList).size() == 1;
    }

}