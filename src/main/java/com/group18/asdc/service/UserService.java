package com.group18.asdc.service;

import com.group18.asdc.entities.User;

public interface UserService {

	Boolean authenticateByEmailAndPassword(String email, String password);

	public boolean isUserExists(User user);

	public User getUserById(String bannerId);
}