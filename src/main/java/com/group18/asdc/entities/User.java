package com.group18.asdc.entities;

public class User {
	
	private String firstName;
	private String lastName;
	private String bannerId;
	private String email;
	public User() {
		super();
	}
	public User(String firstName, String lastName, String bannerId, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.bannerId = bannerId;
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getBannerId() {
		return bannerId;
	}
	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
