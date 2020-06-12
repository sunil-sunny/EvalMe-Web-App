package com.group18.asdc;

import javax.sql.*;

import com.group18.asdc.dao.AdminDao;
import com.group18.asdc.dao.AdminDaoImpl;
import com.group18.asdc.dao.CourseDetailsDao;
import com.group18.asdc.dao.CourseDetailsDaoImpl;
import com.group18.asdc.dao.UserDao;
import com.group18.asdc.dao.UserDaoImpl;
import com.group18.asdc.database.DefaultDatabaseConfiguration;
import com.group18.asdc.database.IDatabaseConfiguration;
import com.group18.asdc.security.BCryptPasswordEncryption;
import com.group18.asdc.security.IPasswordEncryption;
import com.group18.asdc.service.AdminService;
import com.group18.asdc.service.AdminServiceImpl;
import com.group18.asdc.service.CourseDetailsService;
import com.group18.asdc.service.CourseDetailsServiceImpl;
import com.group18.asdc.service.EmailService;
import com.group18.asdc.service.EmailServiceImpl;
import com.group18.asdc.service.Registerservice;
import com.group18.asdc.service.UserService;
import com.group18.asdc.service.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SystemConfig {

	private static SystemConfig singletonInstance = null;

	// Below are the instance objects for service layer
	private AdminService theAdminService;
	private CourseDetailsService theCourseDetailsService;
	private EmailService theEmailService;
	private Registerservice theRegisterservice;
	private UserService theUserService;

	// Below are the instance objects for Dao layer
	private AdminDao theAdminDao;
	private CourseDetailsDao theCourseDetailsDao;
	//private RegisterDao theRegisterDao;
	private UserDao theUserDao;
	private IPasswordEncryption passwordEncryption;
	private IDatabaseConfiguration databaseConfiguration;

	private SystemConfig() {
		this.theAdminService=new AdminServiceImpl();
		this.theCourseDetailsService=new CourseDetailsServiceImpl();
		this.theEmailService=new EmailServiceImpl();
		//this.theRegisterservice=new RegisterserviceImpl();
		this.theUserService=new UserServiceImpl();
		this.theAdminDao=new AdminDaoImpl();
		this.theCourseDetailsDao=new CourseDetailsDaoImpl();
		//this.theRegisterDao=new RegisterDaoImpl();
		this.theUserDao=new UserDaoImpl();
		//
		this.passwordEncryption = new BCryptPasswordEncryption();
		this.databaseConfiguration = new DefaultDatabaseConfiguration();
	}

	public static SystemConfig getSingletonInstance() {
		
		if (null == singletonInstance)
		{
			singletonInstance = new SystemConfig();
		}

		return singletonInstance;
	}

	

	public AdminService getTheAdminService() {
		return theAdminService;
	}

	public void setTheAdminService(AdminService theAdminService) {
		this.theAdminService = theAdminService;
	}

	public CourseDetailsService getTheCourseDetailsService() {
		return theCourseDetailsService;
	}

	public void setTheCourseDetailsService(CourseDetailsService theCourseDetailsService) {
		this.theCourseDetailsService = theCourseDetailsService;
	}

	public EmailService getTheEmailService() {
		return theEmailService;
	}

	public void setTheEmailService(EmailService theEmailService) {
		this.theEmailService = theEmailService;
	}

	public Registerservice getTheRegisterservice() {
		return theRegisterservice;
	}

	public void setTheRegisterservice(Registerservice theRegisterservice) {
		this.theRegisterservice = theRegisterservice;
	}

	public UserService getTheUserService() {
		return theUserService;
	}

	public void setTheUserService(UserService theUserService) {
		this.theUserService = theUserService;
	}

	public AdminDao getTheAdminDao() {
		return theAdminDao;
	}

	public void setTheAdminDao(AdminDao theAdminDao) {
		this.theAdminDao = theAdminDao;
	}

	public CourseDetailsDao getTheCourseDetailsDao() {
		return theCourseDetailsDao;
	}

	public void setTheCourseDetailsDao(CourseDetailsDao theCourseDetailsDao) {
		this.theCourseDetailsDao = theCourseDetailsDao;
	}

	public UserDao getTheUserDao() {
		return theUserDao;
	}

	public void setTheUserDao(UserDao theUserDao) {
		this.theUserDao = theUserDao;
	}

	public IPasswordEncryption getPasswordEncryption(){
		return passwordEncryption;
	}

	public IDatabaseConfiguration getDatabaseConfiguration()
	{
		return databaseConfiguration;
	}
}
