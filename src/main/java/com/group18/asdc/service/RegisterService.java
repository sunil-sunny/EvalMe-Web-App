package com.group18.asdc.service;

import java.util.List;

import com.group18.asdc.entities.Registerbean;
import com.group18.asdc.entities.User;

public interface RegisterService {

	public String registeruser(Registerbean bean);
	public boolean registerStudents(List<User> studentList);

}
