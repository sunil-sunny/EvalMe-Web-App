package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.group18.asdc.dao.UserDao;
import com.group18.asdc.database.SQLMethods;
import com.group18.asdc.database.SQLQueries;
import com.group18.asdc.entities.User;
import com.group18.asdc.util.GroupFormationToolUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SQLMethods sqlImplementation;
    @Autowired
    private DataSource dataSource;
    
    @Override
    public Boolean authenticateByEmailAndPassword(ArrayList<Object> valuesList) throws SQLException {
        return sqlImplementation.selectQuery(SQLQueries.USER_AUTH_BY_EMAIL_PASSWORD.toString(), valuesList).size() == 1;
    }
    
	@Override
	public boolean isUserExists(User user) {
		Connection connection = null;
		ResultSet resultSet = null;
		Statement checkUser = null;
		try {
			connection = dataSource.getConnection();
			String userSql = "select * from CSCI5308_18_DEVINT.user where bannerid='" + user.getBannerId() + "';";
			checkUser = connection.createStatement();
			resultSet = checkUser.executeQuery(userSql);
			if (resultSet.next()) {
				return true;
			} else {

				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (connection != null) {
					connection.close();
				}
				if (checkUser != null) {
					checkUser.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;

	}

	@Override
	public User getUserById(String bannerId) {

		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement getUser = null;
		User user = null;
		try {
			connection = dataSource.getConnection();
			String userSql = GroupFormationToolUtil.getUserById;
			getUser = connection.prepareStatement(userSql);
			getUser.setString(1, bannerId);
			resultSet = getUser.executeQuery();

			while (resultSet.next()) {

				user = new User();
				user.setBannerId(resultSet.getString("bannerid"));
				user.setEmail(resultSet.getString("emailid"));
				user.setFirstName(resultSet.getString("firstname"));
				user.setLastName(resultSet.getString("lastname"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (connection != null) {
					connection.close();
				}
				if (getUser != null) {
					getUser.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return user;
	}

}