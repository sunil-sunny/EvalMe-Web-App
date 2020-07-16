package com.group18.asdc.entities.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.group18.asdc.entities.Group;
import com.group18.asdc.entities.User;
import static org.junit.Assert.assertTrue;

public class GroupTest {

	@Test
	public void getGroupId() {
		Group group = new Group();
		group.setGroupId(1);
		assertTrue(group.getGroupId()==1);
	}
	
	@Test
	public void setGroupId() {
		Group group = new Group();
		group.setGroupId(1);
		assertTrue(group.getGroupId()==1);
	}
	
	@Test
	public void getGroupMembers() {
		User user = new User();
		user.setBannerId("B00842470");
		user.setEmail("lukeskywalker@dal.ca");
		user.setFirstName("Luke");
		user.setLastName("Skywalker");
		List<User> userList = new ArrayList<User>();
		userList.add(user);
		Group group = new Group();
		group.setGroupMembers(userList);
		assertTrue(group.getGroupMembers().equals(userList));
	}
	
	@Test
	public void setGroupMembers() {
		User user = new User();
		user.setBannerId("B00842470");
		user.setEmail("lukeskywalker@dal.ca");
		user.setFirstName("Luke");
		user.setLastName("Skywalker");
		List<User> userList = new ArrayList<User>();
		userList.add(user);
		Group group = new Group();
		group.setGroupMembers(userList);
		assertTrue(group.getGroupMembers().equals(userList));
	}
	
	@Test
	public void getGroupName() {
		Group group = new Group();
		group.setGroupName("First Group");
		assertTrue(group.getGroupName().equals("First Group"));
	}
	
	@Test
	public void setGroupName() {
		Group group = new Group();
		group.setGroupName("First Group");
		assertTrue(group.getGroupName().equals("First Group"));
	}
}