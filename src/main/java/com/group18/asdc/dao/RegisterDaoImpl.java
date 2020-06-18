package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.group18.asdc.database.ConnectionManager;
import com.group18.asdc.entities.UserRegistartionDetails;


public class RegisterDaoImpl implements RegisterDao {

	@Override
	public boolean registeruser(UserRegistartionDetails registerDetails) {

		Connection connection = null;
		PreparedStatement registerUserStatement = null;
		PreparedStatement assignRoleStatement = null;
		boolean isUserRegisterd=false;
		boolean isGuestRoleAssigned=false;

		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			connection.setAutoCommit(false);
			registerUserStatement = connection.prepareStatement("insert into user values(?,?,?,?,?)");

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(registerDetails.getPassword());
			// System.out.println("encrypted password is" + hashedPassword);
			registerUserStatement.setString(1, registerDetails.getBannerid());
			registerUserStatement.setString(2, registerDetails.getLastname());
			registerUserStatement.setString(3, registerDetails.getFirstname());
			registerUserStatement.setString(4, registerDetails.getEmailid());
			registerUserStatement.setString(5, hashedPassword);
			int registerStatus = registerUserStatement.executeUpdate();
			
			if (registerStatus > 0) {
				isUserRegisterd=true;
			}
			assignRoleStatement = connection.prepareStatement("insert into systemrole(roleid,bannerid) values(?,?)");
			assignRoleStatement.setInt(1, 2);
			assignRoleStatement.setString(2, registerDetails.getBannerid());
			int assignRoleResult = assignRoleStatement.executeUpdate();
			
			if (assignRoleResult > 0) {
				System.out.println("The role is addded as a guest user");
				isGuestRoleAssigned=true;
			}
			
			if(isGuestRoleAssigned&&isUserRegisterd) {
				connection.commit();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
				if (registerUserStatement != null) {
					registerUserStatement.close();
				}
				if (assignRoleStatement != null) {
					assignRoleStatement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return isUserRegisterd && isGuestRoleAssigned;
	}

	@Override
	public boolean checkUserWithEmail(String email) {
		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		ResultSet theResultSet = null;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection.prepareStatement("select * from user where emailid=?");
			thePreparedStatement.setString(1, email);
			theResultSet = thePreparedStatement.executeQuery();
			if (theResultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
				if (thePreparedStatement != null) {
					thePreparedStatement.close();
				}
				if (theResultSet != null) {
					theResultSet.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return false;
	}

	@Override
	public boolean checkUserWithBannerId(String bannerId) {

		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		ResultSet theResultSet = null;

		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection.prepareStatement("select * from user where bannerid=?");
			thePreparedStatement.setString(1, bannerId);
			theResultSet = thePreparedStatement.executeQuery();
			if (theResultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
				if (thePreparedStatement != null) {
					thePreparedStatement.close();
				}
				if (theResultSet != null) {
					theResultSet.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return false;
	}

}
