package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.group18.asdc.entities.Course;
import com.group18.asdc.util.GroupFormationToolUtil;

@Repository
public class CourseDetailsDaoImpl implements CourseDetailsDao {

	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

	@Override
	public List<Course> getAllCourses() {

		Connection con = null;
		Statement getCourses = null;
		PreparedStatement getCourseRoles = null;
		ResultSet resultSetAllCourses = null;
		ResultSet resultSetAllCourseRoles = null;
		List<Course> allCourses = new ArrayList<Course>();
		try {
			con = dataSource.getConnection();
			getCourses = con.createStatement();
			resultSetAllCourses = getCourses.executeQuery(GroupFormationToolUtil.getAllCourses);
			getCourseRoles = con.prepareStatement(GroupFormationToolUtil.getCourseDetailsbyId);
			Course course = null;
			while (resultSetAllCourses.next()) {
				course = new Course();
				course.setCourseId(resultSetAllCourses.getInt("courseid"));
				course.setCourseName(resultSetAllCourses.getString("coursename"));
				getCourseRoles.setInt(1, resultSetAllCourses.getInt("courseid"));
				resultSetAllCourseRoles = getCourseRoles.executeQuery();
				while (resultSetAllCourseRoles.next()) {

					if (resultSetAllCourseRoles.getString("rolename").equalsIgnoreCase("INSTRUCTOR")) {
						course.setInstructorName(resultSetAllCourseRoles.getString("instructorname"));
					} else if (resultSetAllCourseRoles.getString("rolename").equalsIgnoreCase("TA")) {
						course.setTaName(resultSetAllCourseRoles.getString("instructorname"));
					}

				}

				resultSetAllCourseRoles.close();
				allCourses.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				getCourses.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return allCourses;
	}

	@Override
	public boolean allocateTa(String courseId, String bannerId) {

		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(
					"insert into CSCI5308_18_DEVINT.courserole (roleid,courseid,bannerid) values (2,?,?);");
			statement.setInt(1, Integer.parseInt(courseId));
			statement.setString(2, bannerId);
			int taAllocated = statement.executeUpdate();
			if (taAllocated > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;

	}

	@Override
	public boolean isUserExists(String bannerId) {
		Connection connection = null;
		ResultSet resultSet = null;
		Statement checkUser = null;
		try {
			connection = dataSource.getConnection();
			String userSql = "select * from CSCI5308_18_DEVINT.user where bannerid='" + bannerId + "';";
			checkUser = connection.createStatement();
			resultSet = checkUser.executeQuery(userSql);
			if (resultSet.next()) {
				System.out.println("user exists");
				return true;
			} else {

				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;

	}

	@Override
	public User getUserById(String bannerId) {

		Connection connection = connector.connect();
		ResultSet resultSet = null;
		PreparedStatement getUser = null;
		User user=new User();
		try {
			String userSql = GroupFormationToolUtil.getUserById;
			getUser=connection.prepareStatement(userSql);
			getUser.setString(1, bannerId);
			resultSet = getUser.executeQuery();
			
			while(resultSet.next()) {
				
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
				resultSet.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return user;
	}

}
