package com.group18.asdc.service;

import com.group18.asdc.GroupFormationConfig;
import com.group18.asdc.dao.GroupFormationDao;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.SurveyGroups;

public class GroupFormationServiceImpl implements GroupFormationService{

	@Override
	public SurveyGroups getGroupFormationResults(Course course) {

		GroupFormationDao theGroupFormationDao = GroupFormationConfig.getSingletonInstance().getTheGroupFormationDao();
		return theGroupFormationDao.getGroupFormationResults(course);
	}
}