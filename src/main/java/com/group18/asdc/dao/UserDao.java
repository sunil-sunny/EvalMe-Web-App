package com.group18.asdc.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.group18.asdc.entities.User;

public interface UserDao {
    
    Boolean authenticateByEmailAndPassword(ArrayList<Object> valueList) throws SQLException;
    public boolean isUserExists(User user);
	public User getUserById(String bannerId);
}