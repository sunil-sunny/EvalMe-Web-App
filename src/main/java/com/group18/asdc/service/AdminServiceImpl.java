package com.group18.asdc.service;

import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import com.group18.asdc.SystemConfig;
import com.group18.asdc.dao.AdminDao;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;
import com.group18.asdc.util.ConstantStringUtil;

@Service
public class AdminServiceImpl implements AdminService {

	private AdminDao admindao;
	private Logger log = Logger.getLogger(AdminServiceImpl.class.getName());

	public AdminServiceImpl() {
		super();
	}

	@Override
	public boolean isCourseIdValid(Course course) {
		int courseId = course.getCourseId();
		if (0 >= courseId || String.valueOf(courseId).length() != 4) {
			return false;
		}
		return true;
	}

	@Override
	public boolean iscreateCourseParametersValid(Course course) {
		UserService theUserService = SystemConfig.getSingletonInstance().getTheUserService();
		CourseDetailsService theCourseDetailsService = SystemConfig.getSingletonInstance().getTheCourseDetailsService();
		if (false == isCourseIdValid(course)) {
			return false;
		}
		if (theCourseDetailsService.isCourseExists(course)) {
			return false;
		}
		String instructorId = course.getInstructorName().getBannerId();
		User instructor = theUserService.getUserById(instructorId);
		if (null == instructor) {
			return false;
		}
		if (instructorId.length() != 9 || false == instructorId.matches(ConstantStringUtil.getBanneridpatterncheck())) {
			return false;
		}
		if (false == theUserService.isUserInstructor(course)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean createCourse(Course course) {
		admindao = SystemConfig.getSingletonInstance().getTheAdminDao();
		log.info("Acceesing Admin Service Impl");
		if (iscreateCourseParametersValid(course)) {
			return admindao.addCourse(course);
		}
		return false;
	}

	@Override
	public boolean deleteCourse(Course course) {
		admindao = SystemConfig.getSingletonInstance().getTheAdminDao();
		log.info("Accessing Admin Service Impl");
		if (isCourseIdValid(course)) {
			return admindao.deleteCourse(course);
		}
		return false;
	}
}