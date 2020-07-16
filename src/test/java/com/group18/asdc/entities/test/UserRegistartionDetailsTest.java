package com.group18.asdc.entities.test;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.group18.asdc.entities.UserRegistartionDetails;

public class UserRegistartionDetailsTest {
	
	@Test
	public void getFirstname() {
		UserRegistartionDetails userDetails = new UserRegistartionDetails();
		userDetails.setFirstname("Luke");
		assertTrue(userDetails.getFirstname().equals("Luke"));
	}

	@Test
	public void setFirstname() {
		UserRegistartionDetails userDetails = new UserRegistartionDetails();
		userDetails.setFirstname("Luke");
		assertTrue(userDetails.getFirstname().equals("Luke"));
	}

	@Test
	public void getLastname() {
		UserRegistartionDetails userDetails = new UserRegistartionDetails();
		userDetails.setLastname("Skywalker");
		assertTrue(userDetails.getLastname().equals("Skywalker"));
	}

	@Test
	public void setLastname() {
		UserRegistartionDetails userDetails = new UserRegistartionDetails();
		userDetails.setLastname("Skywalker");
		assertTrue(userDetails.getLastname().equals("Skywalker"));
	}

	@Test
	public void getBannerid() {
		UserRegistartionDetails userDetails = new UserRegistartionDetails();
		userDetails.setBannerid("B00842470");
		assertTrue(userDetails.getBannerid().equals("B00842470"));
	}

	@Test
	public void setBannerid() {
		UserRegistartionDetails userDetails = new UserRegistartionDetails();
		userDetails.setBannerid("B00842470");
		assertTrue(userDetails.getBannerid().equals("B00842470"));
	}

	@Test
	public void getEmailid() {
		UserRegistartionDetails userDetails = new UserRegistartionDetails();
		userDetails.setEmailid("lukeskywalker@dal.ca");
		assertTrue(userDetails.getEmailid().equals("lukeskywalker@dal.ca"));
	}

	@Test
	public void setEmailid() {
		UserRegistartionDetails userDetails = new UserRegistartionDetails();
		userDetails.setEmailid("lukeskywalker@dal.ca");
		assertTrue(userDetails.getEmailid().equals("lukeskywalker@dal.ca"));
	}

	@Test
	public void getPassword() {
		UserRegistartionDetails userDetails = new UserRegistartionDetails();
		userDetails.setPassword("passwordLuke123");
		assertTrue(userDetails.getPassword().equals("passwordLuke123"));
	}

	@Test
	public void setPassword() {
		UserRegistartionDetails userDetails = new UserRegistartionDetails();
		userDetails.setPassword("passwordLuke123");
		assertTrue(userDetails.getPassword().equals("passwordLuke123"));
	}

	@Test
	public void getConfirmpassword() {
		UserRegistartionDetails userDetails = new UserRegistartionDetails();
		userDetails.setConfirmpassword("passwordLuke123");
		assertTrue(userDetails.getConfirmpassword().equals("passwordLuke123"));
	}

	@Test
	public void setConfirmpassword() {
		UserRegistartionDetails userDetails = new UserRegistartionDetails();
		userDetails.setConfirmpassword("passwordLuke123");
		assertTrue(userDetails.getConfirmpassword().equals("passwordLuke123"));
	}
}
