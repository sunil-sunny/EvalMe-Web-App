package com.advsdc.evalme.service;

public interface UserService {

	Boolean authenticateByEmailAndPassword(String email, String password);
}