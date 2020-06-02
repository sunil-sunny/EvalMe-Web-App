package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
public class Daoimpl {
static Connection con;
static {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_18_DEVINT","CSCI5308_18_DEVINT_USER", "CSCI5308_18_DEVINT_18000");
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}
public static Connection getConnection()
{
    return  con;
}
}
