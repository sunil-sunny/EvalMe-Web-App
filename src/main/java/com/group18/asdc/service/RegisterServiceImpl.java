package com.group18.asdc.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Repository;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.dao.RegisterDao;
import com.group18.asdc.entities.UserRegistartionDetails;
import com.group18.asdc.entities.User;
import com.group18.asdc.errorhandling.PasswordPolicyException;
import com.group18.asdc.util.DataBaseQueriesUtil;

@Repository
public class RegisterServiceImpl implements RegisterService {
	
	private Logger log=Logger.getLogger(RegisterServiceImpl.class.getName());

	@Override
	public String registeruser(UserRegistartionDetails userDetails) {

		if (!userDetails.getBannerid().matches("B00(.*)")) {
			System.out.println("The bannerid is not valid");
			return "invalid bannerid";
		} else if (userDetails.getBannerid().length() != 9) {
			System.out.println("The bannerid is not valid");
			return "invalid bannerid2";
		}

		if (!userDetails.getEmailid().matches("(.*)@dal.ca")) {
			System.out.println("The emailid is not valid");
			return "invalidemailid";
		}

		try {
			User.isPasswordValid(userDetails.getPassword(), SystemConfig.getSingletonInstance().getBasePasswordPolicyManager());
		} catch (PasswordPolicyException e) {
			return "passwordPolicyException="+e.getMessage();
			
		}

		RegisterDao registerDao=SystemConfig.getSingletonInstance().getTheRegisterDao();
		boolean isEmailExits=registerDao.checkUserWithEmail(userDetails.getEmailid());
		boolean isBannerIdExists=registerDao.checkUserWithEmail(userDetails.getBannerid());
		
		if(isBannerIdExists) {
			
			return "Banner Id already exists";
		}	
		if(isEmailExits) {
			return "Email already exists";
		}
		
		boolean registerResult=false;
		if(!isBannerIdExists && !isEmailExits) {
			
			registerResult=registerDao.registeruser(userDetails);
		}
		
		if(registerResult) {
			
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
				String result = this.registeruser(new UserRegistartionDetails(user));
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
