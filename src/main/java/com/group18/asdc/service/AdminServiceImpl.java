package com.group18.asdc.service;

import org.springframework.stereotype.Service;
import com.group18.asdc.CourseConfig;
import com.group18.asdc.ProfileManagementConfig;
import com.group18.asdc.dao.AdminDao;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;
import com.group18.asdc.util.ConstantStringUtil;

@Service
public class AdminServiceImpl implements AdminService {

	private AdminDao admindao;

	public AdminServiceImpl() {
		super();
	}

	@Override
	public boolean isCourseIdValid(Course course) {
		int courseId = course.getCourseId();
		if (0 >= courseId || 4 != String.valueOf(courseId).length()) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	@Override
	public boolean iscreateCourseParametersValid(Course course) {
		UserService theUserService = ProfileManagementConfig.getSingletonInstance().getTheUserService();
		CourseDetailsService theCourseDetailsService = CourseConfig.getSingletonInstance().getTheCourseDetailsService();
		admindao = ProfileManagementConfig.getSingletonInstance().getTheAdminDao();
		if (isCourseIdValid(course)) {
			if (theCourseDetailsService.isCourseExists(course)) {
				return Boolean.FALSE;
			} else {
				String instructorId = course.getInstructorName().getBannerId();
				User instructor = theUserService.getUserById(instructorId);
				if (null == instructor) {
					return Boolean.FALSE;
				} else {
					if (9 == instructorId.length()
							|| instructorId.matches(ConstantStringUtil.BANNER_ID_CHECK.toString())) {
						if (theUserService.isUserInstructor(course)) {
							return Boolean.FALSE;
						}
					} else {
						return Boolean.FALSE;
					}
				}
			}
		} else {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	@Override
	public boolean createCourse(Course course) {
		admindao = ProfileManagementConfig.getSingletonInstance().getTheAdminDao();
		if (iscreateCourseParametersValid(course)) {
			return admindao.addCourse(course);
		}
		return Boolean.FALSE;
	}

	@Override
	public boolean deleteCourse(Course course) {
		admindao = ProfileManagementConfig.getSingletonInstance().getTheAdminDao();
		if (isCourseIdValid(course)) {
			return admindao.deleteCourse(course);
		}
		return Boolean.FALSE;
	}
}