package com.group18.asdc.entities.test;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;

public class CourseTest {

	@Test
	public void getCourseId() {
		Course course = new Course();
		course.setCourseId(1995);
		assertTrue(course.getCourseId() == 1995);
	}

	@Test
	public void setCourseId() {
		Course course = new Course();
		course.setCourseId(8888);
		assertTrue(course.getCourseId() == 8888);
	}

	@Test
	public void getCourseName() {
		Course course = new Course();
		course.setCourseName("Mobile Computing");
		assertTrue(course.getCourseName().equals("Mobile Computing"));
	}

	@Test
	public void setCourseName() {
		Course course = new Course();
		course.setCourseName("Mobile Computing");
		assertTrue(course.getCourseName().equals("Mobile Computing"));
	}

	@Test
	public void getInstructorName() {
		User user = new User();
		user.setBannerId("B00842470");
		Course course = new Course();
		course.setInstructorName(user);
		assertTrue(course.getInstructorName().equals(user));
	}

	@Test
	public void setInstructorName() {
		User user = new User();
		user.setBannerId("B00842470");
		Course course = new Course();
		course.setInstructorName(user);
		assertTrue(course.getInstructorName().equals(user));
	}

	@Test
	public void getTaName() {
		List<User> taList = new ArrayList<User>();
		User user = new User();
		user.setBannerId("B00737373");
		User anotherUser = new User();
		anotherUser.setBannerId("B00842470");
		taList.add(user);
		taList.add(anotherUser);
		Course course = new Course();
		course.setTaList(taList);
		assertTrue(course.getTaList().equals(taList));
	}

	@Test
	public void setTaName() {
		List<User> taListName = new ArrayList<User>();
		User user = new User();
		user.setBannerId("B00737373");
		User anotherUser = new User();
		anotherUser.setBannerId("B00842470");
		taListName.add(user);
		taListName.add(anotherUser);
		Course course = new Course();
		course.setTaList(taListName);
		assertTrue(course.getTaList().equals(taListName));
	}

	@Test
	public void getTaList() {
		List<User> getTaList = new ArrayList<User>();
		User user = new User();
		user.setBannerId("B00737373");
		user.setFirstName("Han");
		user.setLastName("Solo");
		user.setEmail("hansolo@dal.ca");
		User anotherUser = new User();
		anotherUser.setBannerId("B00222222");
		anotherUser.setFirstName("Kylo");
		anotherUser.setLastName("Ren");
		anotherUser.setEmail("kyloren@dal.ca");
		getTaList.add(user);
		getTaList.add(anotherUser);
		Course course = new Course();
		course.setTaList(getTaList);
		assertTrue(course.getTaList().equals(getTaList));
	}
	

	@Test
	public void setTaList() {
		List<User> setTaList = new ArrayList<User>();
		User user = new User();
		user.setBannerId("B00737373");
		user.setFirstName("Han");
		user.setLastName("Solo");
		user.setEmail("hansolo@dal.ca");
		User anotherUser = new User();
		anotherUser.setBannerId("B00222222");
		anotherUser.setFirstName("Kylo");
		anotherUser.setLastName("Ren");
		anotherUser.setEmail("kyloren@dal.ca");
		setTaList.add(user);
		setTaList.add(anotherUser);
		Course course = new Course();
		course.setTaList(setTaList);
		assertTrue(course.getTaList().equals(setTaList));
	}

	@Test
	public void getStudentList() {
		List<User> getStudentList = new ArrayList<User>();
		User user = new User();
		user.setBannerId("B00888888");
		user.setFirstName("Luke");
		user.setLastName("Skywalker");
		user.setEmail("lukeskywalker@dal.ca");
		User anotherUser = new User();
		anotherUser.setBannerId("B00454545");
		anotherUser.setFirstName("Alice");
		anotherUser.setLastName("McMaster");
		anotherUser.setEmail("alicemcmaster@dal.ca");
		getStudentList.add(user);
		getStudentList.add(anotherUser);
		Course course = new Course();
		course.setStudentList(getStudentList);
		assertTrue(course.getStudentList().equals(getStudentList));
	}

	@Test
	public void setStudentList() {
		List<User> setStudentList = new ArrayList<User>();
		User user = new User();
		user.setBannerId("B00888888");
		user.setFirstName("Luke");
		user.setLastName("Skywalker");
		user.setEmail("lukeskywalker@dal.ca");
		User anotherUser = new User();
		anotherUser.setBannerId("B00454545");
		anotherUser.setFirstName("Alice");
		anotherUser.setLastName("McMaster");
		anotherUser.setEmail("alicemcmaster@dal.ca");
		setStudentList.add(user);
		setStudentList.add(anotherUser);
		Course course = new Course();
		course.setStudentList(setStudentList);
		assertTrue(course.getStudentList().equals(setStudentList));
	}

}
