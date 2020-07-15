package com.group18.asdc.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.group18.asdc.CourseConfig;
import com.group18.asdc.ProfileManagementConfig;
import com.group18.asdc.dao.CourseRolesDao;
import com.group18.asdc.entities.User;
import com.group18.asdc.errorhandling.FileProcessingException;
import com.group18.asdc.util.ConstantStringUtil;

public class CourseRolesServiceImpl implements CourseRolesService {

	@Override
	public boolean allocateTa(int courseId, User user) {
		CourseDetailsService theCourseDetailsService = CourseConfig.getSingletonInstance().getTheCourseDetailsService();
		List<User> taAsList = new ArrayList<User>();
		List<User> eligibleUser = null;
		CourseRolesDao courseRolesDao = CourseConfig.getSingletonInstance().getTheCourseRolesDao();
		if (null != user) {
			taAsList.add(user);
			eligibleUser = theCourseDetailsService.filterEligibleUsersForCourse(taAsList, courseId);
		}
		if (null != eligibleUser && 0 != eligibleUser.size()) {
			return courseRolesDao.allocateTa(courseId, user);
		}
		return Boolean.FALSE;
	}

	@Override
	public boolean enrollStuentsIntoCourse(List<User> studentList, int courseId) {
		CourseRolesDao courseRolesDao = CourseConfig.getSingletonInstance().getTheCourseRolesDao();
		CourseDetailsService theCourseDetailsService = CourseConfig.getSingletonInstance().getTheCourseDetailsService();
		RegisterService theRegisterService = ProfileManagementConfig.getSingletonInstance().getTheRegisterservice();
		boolean isStudentsRegistered = theRegisterService.registerStudents(studentList);
		if (isStudentsRegistered) {
			List<User> eligibleStudents = theCourseDetailsService.filterEligibleUsersForCourse(studentList, courseId);
			if (0 == eligibleStudents.size()) {
				return Boolean.FALSE;
			} else {
				return courseRolesDao.enrollStudentsIntoCourse(eligibleStudents, courseId);
			}
		} else {
			return Boolean.FALSE;
		}
	}

	@Override
	public List<User> extraxtValidStudentsFromFile(MultipartFile file) throws FileProcessingException {
		List<User> validUsers = new ArrayList<User>();
		List<User> inValidUsers = new ArrayList<User>();
		if (file.isEmpty()) {
			throw new FileProcessingException("uploaded file is empty, Kindly upload valid file");
		} else {
			try {
				byte[] bytes = file.getBytes();
				ByteArrayInputStream inputFilestream = new ByteArrayInputStream(bytes);
				BufferedReader br = new BufferedReader(new InputStreamReader(inputFilestream));
				String line = "";
				br.readLine();
				User user = null;
				while (null != (line = br.readLine())) {
					user = new User();
					String userDetails[] = line.split(",");
					if (4 == userDetails.length) {
						String firstName = userDetails[0];
						String lastName = userDetails[1];
						String bannerId = userDetails[2];
						String email = userDetails[3];
						if (bannerId.matches(ConstantStringUtil.BANNER_ID_CHECK.toString()) || bannerId.length() == 9
								|| email.matches(ConstantStringUtil.EMAIL_PATTERN_CHECK.toString())) {
							user.setFirstName(firstName);
							user.setLastName(lastName);
							user.setBannerId(bannerId);
							user.setEmail(email);
							validUsers.add(user);
						} else {
							inValidUsers.add(user);
						}
					} else {
						throw new FileProcessingException(
								"Content in the file doesnt match the format kinldy rectify it ang upload again");
					}
				}
				br.close();
			} catch (IOException e) {
				throw new FileProcessingException("File doesnt exist or it is invalid, Kinldy try again");
			}
		}
		return validUsers;
	}
}