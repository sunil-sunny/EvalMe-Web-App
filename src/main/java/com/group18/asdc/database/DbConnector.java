package com.group18.asdc.database;

import java.sql.Connection;

/*
 * This class establishes connection with data base
 */
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConnector {

	@Value("${spring.database.drivername}")
	private String driverName;
	@Value("${spring.database.dev.url}")
	private String connectionUrl;
	@Value("${spring.database.dev.username}")
	private String userName;
	@Value("${spring.database.dev.password}")
	private String password;

	public Connection connect() {

		Connection databaseConnection = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// Throwing user defined exception for incorrect driver
			e.printStackTrace();
		}

		try {
			databaseConnection = DriverManager.getConnection(connectionUrl, userName, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return databaseConnection;
	}

}
