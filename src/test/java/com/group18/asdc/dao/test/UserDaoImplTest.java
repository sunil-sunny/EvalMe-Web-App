package com.group18.asdc.dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.group18.asdc.dao.UserDao;
import com.group18.asdc.dao.UserDaoImpl;
import com.group18.asdc.database.SQLMethods;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;

@SpringBootTest
public class UserDaoImplTest {

	@InjectMocks
	UserDao userDao = new UserDaoImpl();

	@Mock
	SQLMethods sqlMethods;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	// test for isUserExists method
	@Test
	public void isUserExistsTestOne() {

		final UserDao userDaoImplMock = new UserDaoImplMock();
		final User user = null;
		final boolean isExist = userDaoImplMock.isUserExists(user);
		assertFalse(isExist);
	}

	@Test
	public void isUserExistsTestTwo() {

		final UserDao userDaoImplMock = new UserDaoImplMock();
		final User user = new User("Justin", "Langer", "B00123456", "justin@dal.ca");
		final boolean isExist = userDaoImplMock.isUserExists(user);
		assertTrue(isExist);

	}

	@Test
	public void isUserExistsTestThree() {

		final UserDao userDaoImplMock = new UserDaoImplMock();
		final User user = new User("Sachin", "Tendulkar", "B00999999", "sachin@dal.ca");
		final boolean isExist = userDaoImplMock.isUserExists(user);
		assertFalse(isExist);
	}

	// test for getUserById method
	/*
	 * First test is to send banner id which not exists and it should return null
	 * object
	 */

	@Test
	public void getUserByIdTestOne() {

		final UserDao userDaoImplMock = new UserDaoImplMock();
		final User user = userDaoImplMock.getUserById("B00111111");
		assertNull(user);
	}

	/*
	 * Second Test is send the empty string as we should get a null object again
	 */
	@Test
	public void getUserByIdTestTwo() {
		
		final UserDao userDaoImplMock = new UserDaoImplMock();
		final User user = userDaoImplMock.getUserById("");
		assertNull(user);
	}

	/*
	 * Third test is to send a valid banner id and we should get the valid user
	 * object
	 */
	@Test
	public void getUserByIdTestThree() {

		final UserDao userDaoImplMock = new UserDaoImplMock();
		final User user = userDaoImplMock.getUserById("B00123456");
		assertNotNull(user);
	}

	

	/*
	 * Below test is done by sending valid course id and and we should get all 5
	 * users for course id 1
	 */

	@Test
	public void getAllUsersByCourseTestOne() {

		final UserDao userDaoImplMock = new UserDaoImplMock();
		final List<User> allUsers = userDaoImplMock.getAllUsersByCourse(1);
		assertEquals(5, allUsers.size());
	}

	/*
	 * second test is done by sending invalid course id and we get zero users for
	 * that
	 */
	@Test
	public void getAllUsersByCourseTestTwo() {

		final UserDao userDaoImplMock = new UserDaoImplMock();
		final List<User> allUsers = userDaoImplMock.getAllUsersByCourse(10);
		assertEquals(0, allUsers.size());
	}
	
	@Test
	public void isUserInstructorTest() {
		 final UserDao theUserDao=new UserDaoImplMock();
		 Course theCourse=new Course();
		 boolean isUserExists=theUserDao.isUserInstructor(theCourse);
		 assertTrue(isUserExists);
	}

	private ArrayList getDefaultUserObj() {
		final ArrayList<HashMap<String, Object>> userList = new ArrayList<HashMap<String, Object>>();
		//
		final HashMap valueMap = new HashMap<>();
		valueMap.put("bannerid", "B00838575");
		valueMap.put("emailid", "kr630601@dal.ca");
		valueMap.put("password", "karthikk");
		valueMap.put("firstname", "Karthikk");
		valueMap.put("lastname", "Tamil");

		userList.add(valueMap);

		return userList;
	}

}
