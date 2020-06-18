//package com.group18.asdc.service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.group18.asdc.SystemConfig;
//import com.group18.asdc.dao.CourseDetailsDao;
//import com.group18.asdc.dao.CourseRolesDao;
//import com.group18.asdc.entities.User;
//
//public class CourseRolesServiceImpl implements CourseRolesService {
//
//	@Override
//	public boolean allocateTa(int courseId, User user) {
//
//		// inserting user in a list since the filter users methods takes the arraylist
//		// as input
//		List<User> taAsList = new ArrayList<User>();
//		// User user = userService.getUserById(bannerId);
//		List<User> eligibleUser = null;
//		CourseRolesDao courseRolesDao = SystemConfig.getSingletonInstance().getTheCourseRolesDao();
//		UserService userService = SystemConfig.getSingletonInstance().getTheUserService();
//
//		if (user != null) {
//			taAsList.add(user);
//			eligibleUser = userService.filterEligibleUsersForCourse(taAsList, courseId);
//		}
//
//		if (eligibleUser != null && eligibleUser.size() != 0) {
//
//			return courseRolesDao.allocateTa(courseId, user);
//		}
//
//		return false;
//	}
//
//	@Override
//	public boolean enrollStuentsIntoCourse(List<User> studentList, int courseId) {
//		CourseRolesDao courseRolesDao = SystemConfig.getSingletonInstance().getTheCourseRolesDao();
//		UserService userService = SystemConfig.getSingletonInstance().getTheUserService();
//		RegisterService theRegisterService = SystemConfig.getSingletonInstance().getTheRegisterservice();
//		boolean isStudentsRegistered = theRegisterService.registerStudents(studentList);
//		if (isStudentsRegistered) {
//			List<User> eligibleStudents = userService.filterEligibleUsersForCourse(studentList, courseId);
//
//			return courseRolesDao.enrollStudentsIntoCourse(eligibleStudents, courseId);
//		} else {
//			return false;
//		}
//	}
//
//}
