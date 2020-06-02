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
import com.group18.asdc.entities.User;
import com.group18.asdc.util.GroupFormationToolUtil;

@Repository
public class CourseDetailsDaoImpl implements CourseDetailsDao {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDao userDao;

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
			getCourseRoles = con.prepareStatement(GroupFormationToolUtil.getCourseDetails);
			Course course = null;
			while (resultSetAllCourses.next()) {
				course = new Course();
				List<User> students = new ArrayList<User>();
				List<User> taList = new ArrayList<User>();
				course.setCourseId(resultSetAllCourses.getInt("courseid"));
				course.setCourseName(resultSetAllCourses.getString("coursename"));
				getCourseRoles.setInt(1, resultSetAllCourses.getInt("courseid"));
				resultSetAllCourseRoles = getCourseRoles.executeQuery();
				while (resultSetAllCourseRoles.next()) {

					String role = resultSetAllCourseRoles.getString("rolename");
					String bannerId = resultSetAllCourseRoles.getString("bannerid");
					if (role.equalsIgnoreCase("INSTRUCTOR")) {

						course.setInstructorName(userDao.getUserById(bannerId));
					} else if (role.equalsIgnoreCase("STUDENT")) {

						students.add(userDao.getUserById(bannerId));
					} else if (role.equalsIgnoreCase("TA")) {

						taList.add(userDao.getUserById(bannerId));
					}
				}
				course.setTaList(taList);
				course.setStudentList(students);
				resultSetAllCourseRoles.close();
				allCourses.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (getCourses != null) {
					getCourses.close();
				}
				if (con != null) {
					con.close();
				}
				if (getCourseRoles != null) {
					getCourseRoles.close();
				}
				if (resultSetAllCourses != null) {
					resultSetAllCourses.close();
				}
				if (resultSetAllCourseRoles != null) {
					resultSetAllCourseRoles.close();
				}

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
			statement = connection.prepareStatement(GroupFormationToolUtil.allocateTa);
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
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;

	}



	@Override
	public List<Course> getCourseWhereUserIsInstrcutor(String bannerid) {

		return null;
	}

	@Override
	public List<User> filterEligibleUsersForCourse(List<User> studentList, String courseId) {

		// Returns the list of eligible users to get enrolled in the course.

		List<User> eligibleStudents = new ArrayList<User>();
		List<User> existingStudentsOfCourse = this.getStudentsByCourse(courseId);

		for (User student : studentList) {

			boolean isExists = false;
			for (User existingStudent : existingStudentsOfCourse) {

				if (student.getBannerId().equalsIgnoreCase(existingStudent.getBannerId())) {
					isExists = true;
					break;
				}
			}
			if (!isExists) {
				eligibleStudents.add(student);
			}
		}

		return eligibleStudents;
	}

	@Override
	public List<User> getStudentsByCourse(String courseId) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSetForStudentList = null;
		List<User> studentList = new ArrayList<User>();
		User user = null;

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(GroupFormationToolUtil.getAlluserRelatedToCourse);

			preparedStatement.setInt(1, Integer.parseInt(courseId));
			resultSetForStudentList = preparedStatement.executeQuery();

			while (resultSetForStudentList.next()) {
				user = new User();
				user.setBannerId(resultSetForStudentList.getString("bannerid"));
				user.setEmail(resultSetForStudentList.getString("emailid"));
				user.setFirstName(resultSetForStudentList.getString("firstname"));
				user.setLastName(resultSetForStudentList.getString("lastname"));
				studentList.add(user);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (resultSetForStudentList != null) {
					resultSetForStudentList.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return studentList;
	}

	@Override
	public boolean enrollStudentsIntoCourse(List<User> studentList, String courseId) {

		Connection connection = null;
		PreparedStatement queryToEnrollStudent = null;

		boolean enrollStatus = false;
		try {
			connection = dataSource.getConnection();

			for (User user : studentList) {
				queryToEnrollStudent = connection.prepareStatement(GroupFormationToolUtil.enrollStudentIntoCourse);
				queryToEnrollStudent.setInt(1, Integer.parseInt(courseId));
				queryToEnrollStudent.setString(2, user.getBannerId());
				int isEnrolled = queryToEnrollStudent.executeUpdate();

				if (isEnrolled == 1) {
					enrollStatus = true;
				}
				queryToEnrollStudent.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
				if (queryToEnrollStudent != null) {
					queryToEnrollStudent.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return enrollStatus;
	}

}
