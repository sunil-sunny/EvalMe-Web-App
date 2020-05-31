package com.group18.asdc.service;

public interface UserService {

	Boolean authenticateByEmailAndPassword(String email, String password);
}