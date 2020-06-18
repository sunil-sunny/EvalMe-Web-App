package com.group18.asdc.service;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;
import com.group18.asdc.SystemConfig;
import com.group18.asdc.dao.AdminDao;
import com.group18.asdc.entities.Course;

@Service
public class AdminServiceImpl implements AdminService {

	AdminDao admindao;

	public AdminServiceImpl() {
		super();
	}

	private Logger log = Logger.getLogger(AdminServiceImpl.class.getName());

	@Override
	public boolean isCourseIdValid(Course course) {

		admindao=SystemConfig.getSingletonInstance().getTheAdminDao();

		int courseId = course.getCourseId();
		if (0 >= courseId || 4 != String.valueOf(courseId).length()){
			return false;
		}
		return true;
	}

	@Override
	public boolean iscreateCourseParametersValid(Course course) {

		admindao=SystemConfig.getSingletonInstance().getTheAdminDao();

		if(false == isCourseIdValid(course)) {
			return false;
		}
		if(true==admindao.isCourseExists(course)) {
			return false;
		}

		String instructorId = course.getInstructorName().getBannerId();

		if ( instructorId.length() !=9 || !instructorId.matches("B00(.*)")) {
			return false;
		}
		if(true == admindao.isUserInstructor(course)) {
			return false;
		}
		return true;
	}


	@Override
	public boolean createCourse(Course course){

		admindao=SystemConfig.getSingletonInstance().getTheAdminDao();

		if(true == iscreateCourseParametersValid(course)) {
			return admindao.addCourse(course);
		}

		return false;
	}

	@Override
	public boolean deleteCourse(Course course) {

		admindao=SystemConfig.getSingletonInstance().getTheAdminDao();

		if(true == isCourseIdValid(course) ) {
			return admindao.deleteCourse(course);
		}

		return false;
	}

}
