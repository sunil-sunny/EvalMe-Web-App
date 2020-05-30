package com.advsdc.evalme.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDao {
    
    Boolean authenticateByEmailAndPassword(ArrayList<Object> valueList) throws SQLException;
}