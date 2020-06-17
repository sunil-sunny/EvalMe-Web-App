package com.group18.asdc.service.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.group18.asdc.entities.User;

public class CourseRolesServiceImplTest {

	/*
	 * Below test send set of students and ideally the result will be true if they
	 * are added.
	 */
	@Test
	public void enrollStudentsIntoCourseTestOne() {

		CourseRolesServiceMock theCourseRolesServiceMock = new CourseRolesServiceMock();
		User user = new User("Rahul", "Dravid", "B09896157", "dravid@dal.ca");
		User userOne = new User("Rahul", "Chahar", "B09898157", "chahar@dal.ca");
		List<User> studentsList = new ArrayList<User>();
		studentsList.add(user);
		studentsList.add(userOne);
		boolean isEnrolled = theCourseRolesServiceMock.enrollStuentsIntoCourse(studentsList, 5);
		assertTrue(isEnrolled);
	}

	/*
	 * Below test send set of empty students and ideally the result will be false if
	 * they are added.
	 */
	@Test
	public void enrollStudentsIntoCourseTestTwo() {

		CourseRolesServiceMock theCourseRolesServiceMock = new CourseRolesServiceMock();

		List<User> studentsList = new ArrayList<User>();

		boolean isEnrolled = theCourseRolesServiceMock.enrollStuentsIntoCourse(studentsList, 9);
		assertFalse(isEnrolled);
	}

	/*
	 * Below test passes the invalid users and get false from method
	 */
	@Test
	public void allocateTaTestOne() {

		CourseRolesServiceMock theCourseRolesServiceMock = new CourseRolesServiceMock();
		User studentsList = null;
		boolean isEnrolled = theCourseRolesServiceMock.allocateTa(2, studentsList);
		assertFalse(isEnrolled);

	}

	/*
	 * Below test passes the valid users and get true from method
	 */
	@Test
	public void allocateTaTestTwo() {

		CourseRolesServiceMock theCourseRolesServiceMock = new CourseRolesServiceMock();
		User studentsList = new User("Rahul", "Chahar", "B09898157", "chahar@dal.ca");
		;
		boolean isEnrolled = theCourseRolesServiceMock.allocateTa(2, studentsList);
		assertTrue(isEnrolled);

	}
}
