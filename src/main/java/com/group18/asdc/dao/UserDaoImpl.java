package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.group18.asdc.database.ConnectionManager;
import com.group18.asdc.database.SQLMethods;
import com.group18.asdc.database.SQLStatus;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;
import com.group18.asdc.util.UserManagementDataBaseQueriesUtil;

import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

	private Logger logger = Logger.getLogger(UserDaoImpl.class.getName());

	@Override
	public boolean isUserExists(User user) {

		boolean isUserExits = Boolean.FALSE;
		try (Connection connection = ConnectionManager.getInstance().getDBConnection();
				PreparedStatement checkUser = connection
						.prepareStatement(UserManagementDataBaseQueriesUtil.IS_USER_EXISTS.toString());){
			
			checkUser.setString(1, user.getBannerId());
			ResultSet resultSet = checkUser.executeQuery();
			if (resultSet.next()) {
				isUserExits = Boolean.TRUE;
				logger.log(Level.INFO, "User with id " + user.getBannerId() + " is exists");
			} else {
				isUserExits = Boolean.FALSE;
				logger.log(Level.FINE, "User with id " + user.getBannerId() + " is not exists");
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE,
					"SQL Exception occurred while checking if user exists or not for user id " + user.getBannerId());
		} 
		return isUserExits;
	}

	@Override
	public User getUserById(String bannerId) {

		User user = null;
		try (Connection connection = ConnectionManager.getInstance().getDBConnection();
				PreparedStatement getUser = connection
						.prepareStatement(UserManagementDataBaseQueriesUtil.GET_USER_BY_ID.toString());){
			
			getUser.setString(1, bannerId);
			ResultSet resultSet = getUser.executeQuery();
			while (resultSet.next()) {
				user = new User();
				user.setBannerId(resultSet.getString("bannerid"));
				user.setEmail(resultSet.getString("emailid"));
				user.setFirstName(resultSet.getString("firstname"));
				user.setLastName(resultSet.getString("lastname"));
			}

		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQL Exception while getting user with banner id " + bannerId);
		} 
		return user;
	}

	@Override
	public List<User> getAllUsersByCourse(int courseId) {

		List<User> studentList = new ArrayList<User>();
		User user = null;
		
		try (Connection connection = ConnectionManager.getInstance().getDBConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(UserManagementDataBaseQueriesUtil.GET_ALL_USERS_RELATED_TO_COURSE.toString());){
			
			preparedStatement.setInt(1, courseId);
			ResultSet resultSetForStudentList = preparedStatement.executeQuery();
			while (resultSetForStudentList.next()) {
				user = new User();
				user.setBannerId(resultSetForStudentList.getString("bannerid"));
				user.setEmail(resultSetForStudentList.getString("emailid"));
				user.setFirstName(resultSetForStudentList.getString("firstname"));
				user.setLastName(resultSetForStudentList.getString("lastname"));
				studentList.add(user);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQL Exception while getting all the users realted to course with id " + courseId);
		} 
		return studentList;
	}

	@Override
	public int loadUserWithBannerId(ArrayList<Object> valueList, User userObj) {
		logger.log(Level.INFO, "Loading user object values from db for user=" + valueList.get(0));
		SQLMethods sqlImplementation = null;
		int sqlCodes = SQLStatus.NO_DATA_AVAILABLE;
		try {
			Connection connection = ConnectionManager.getInstance().getDBConnection();
			sqlImplementation = new SQLMethods(connection);
			ArrayList<HashMap<String, Object>> rowsList = sqlImplementation
					.selectQuery(UserManagementDataBaseQueriesUtil.GET_USER_WITH_BANNER_ID.toString(), valueList);
			if (rowsList.size() > 0) {
				HashMap<String, Object> valuesMap = rowsList.get(0);
				userObj.setBannerId((String) valuesMap.get("bannerid"));
				userObj.setEmail((String) valuesMap.get("emailid"));
				userObj.setFirstName((String) valuesMap.get("firstname"));
				userObj.setLastName((String) valuesMap.get("lastname"));
				userObj.setPassword((String) valuesMap.get("password"));
				sqlCodes = SQLStatus.SUCCESSFUL;
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQL Exception while loading user object", e);
			sqlCodes = SQLStatus.SQL_ERROR;
		} finally {
			/*
			 * Had a discussion with Professor Rob and this cannot be avoided without
			 * complicating the code
			 */
			if (null != sqlImplementation) {
				sqlImplementation.cleanup();
			}
		}
		return sqlCodes;
	}

	@Override
	public Boolean updatePassword(ArrayList<Object> criteriaList, ArrayList<Object> valueList) {
		logger.log(Level.INFO, "Updating password in the database for user=" + criteriaList.get(0));
		SQLMethods sqlImplementation = null;
		boolean isUpdateSuccessful = Boolean.FALSE;
		try {
			Connection connection = ConnectionManager.getInstance().getDBConnection();
			sqlImplementation = new SQLMethods(connection);
			Integer rowCount = sqlImplementation.updateQuery(UserManagementDataBaseQueriesUtil.UPDATE_PASSWORD_FOR_USER.toString(), valueList,
					criteriaList);
			isUpdateSuccessful = rowCount > 0;
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQL Exception while updating password", e);
		} finally {
			/*
			 * Had a discussion with Professor Rob and this cannot be avoided without
			 * complicating the code
			 */
			if (null != sqlImplementation) {
				sqlImplementation.cleanup();
			}
		}
		return isUpdateSuccessful;
	}

	@Override
	public ArrayList getUserRoles(ArrayList<Object> criteriaList) {
		logger.log(Level.INFO, "Fetching user roles from the database for user=" + criteriaList.get(0));
		ArrayList rolesList = new ArrayList<>();
		SQLMethods sqlImplementation = null;
		try {
			Connection connection = ConnectionManager.getInstance().getDBConnection();
			sqlImplementation = new SQLMethods(connection);
			ArrayList<HashMap<String, Object>> valuesList = sqlImplementation
					.selectQuery(UserManagementDataBaseQueriesUtil.GET_USER_ROLES.toString(), criteriaList);
			if (valuesList == null || valuesList.size() == 0) {
				rolesList = new ArrayList<>();
			}
			else{
				for (HashMap valueMap : valuesList) {
					rolesList.add(valueMap.get("rolename"));
				}
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQL Exception", e);
		} finally {
			/*
			 * Had a discussion with Professor Rob and this cannot be avoided without
			 * complicating the code
			 */
			if (null != sqlImplementation) {
				sqlImplementation.cleanup();
			}
		}
		return rolesList;
	}

	@Override
	public boolean isUserInstructor(Course course) {
		
		boolean returnValue = Boolean.TRUE;
		String instructorId = course.getInstructorName().getBannerId();
		int courseId = course.getCourseId();
		
		try (Connection connection = ConnectionManager.getInstance().getDBConnection();
				PreparedStatement statement = connection
						.prepareStatement(UserManagementDataBaseQueriesUtil.IS_USER_EXISTS.toString());
				PreparedStatement statementInstructorStudent = connection
						.prepareStatement(UserManagementDataBaseQueriesUtil.IS_INSTRUCTOR_A_STUDENT.toString());){
			
			statement.setString(1, instructorId);
			ResultSet resultset = statement.executeQuery();
			
			if (null == resultset) {
				returnValue = Boolean.FALSE;
			} else {
				statementInstructorStudent.setString(1, instructorId);
				statementInstructorStudent.setInt(2, courseId);
				resultset = statementInstructorStudent.executeQuery();
				if (null != resultset) {
					returnValue = Boolean.FALSE;
				}
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE,
					"SQL Exception while Checking the user as instructor or not for course " + course.getCourseId());
		} 
		return returnValue;
	}
}