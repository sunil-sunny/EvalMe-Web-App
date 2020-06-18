package com.group18.asdc.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Repository;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.dao.RegisterDao;
import com.group18.asdc.entities.Registerbean;
import com.group18.asdc.entities.User;
import com.group18.asdc.errorhandling.PasswordPolicyException;
import com.group18.asdc.util.DataBaseQueriesUtil;

@Repository
public class RegisterServiceImpl implements RegisterService {
	
	private Logger log=Logger.getLogger(RegisterServiceImpl.class.getName());

	@Override
	public String registeruser(Registerbean bean) {

		System.out.println("Registering");
		System.out.println(bean.getBannerid());
		System.out.println(bean.getEmailid());
		if (!bean.getBannerid().matches("B00(.*)")) {
			System.out.println("The bannerid is not valid");
			return "invalidbannerid";
		} else if (bean.getBannerid().length() != 9) {
			System.out.println("The bannerid is not valid");
			return "invalidbannerid2";
		}

		if (!bean.getEmailid().matches("(.*)@dal.ca")) {
			System.out.println("The emailid is not valid");
			return "invalidemailid";
		}

		try {
			User.isPasswordValid(bean.getPassword(), SystemConfig.getSingletonInstance().getBasePasswordPolicyManager());
		} catch (PasswordPolicyException e) {
			return "passwordPolicyException="+e.getMessage();
			
		}

		RegisterDao registerDao=SystemConfig.getSingletonInstance().getTheRegisterDao();
		boolean isEmailExits=registerDao.checkUserWithEmail(bean.getEmailid());
		boolean isBannerIdExists=registerDao.checkUserWithEmail(bean.getBannerid());
		
		if(isBannerIdExists) {
			
			return "Banner Id already exists";
		}
		
		if(isEmailExits) {
			return "Email already exists";
		}
		
		boolean result=false;
		if(!isBannerIdExists && !isEmailExits) {
			
			result=registerDao.registeruser(bean);
		}
		
		if(result) {
			
			return "Success";
		}


		return "User not Registered";
	}
	
	
	@Override
	public boolean registerStudents(List<User> studentList) {
		
		UserService userService = SystemConfig.getSingletonInstance().getTheUserService();
		EmailService emailService = null;
		boolean isAllStudentsRegistered=false;
		for (User user : studentList) {
			if (!userService.isUserExists(user)) {
				String result = this.registeruser(new Registerbean(user));
				if (result.equalsIgnoreCase("success")) {
					emailService=SystemConfig.getSingletonInstance().getTheEmailService();
					String messageText = "Thank you for being a part of us !! \n  you username is " + user.getBannerId()
							+ " and the password is " + user.getBannerId().concat(DataBaseQueriesUtil.passwordTag);
					emailService.sendSimpleMessage(user.getEmail(), "you are now a part of EvalMe", messageText);
					isAllStudentsRegistered=true;
				} else {
					log.info("user registartion error");
				}
			}
		}
		return isAllStudentsRegistered;
	}
}
