package com.group18.asdc.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.SurveyGroups;

public interface GroupFormationDao {
	
	public SurveyGroups getGroupFormationResults(Course course);

}
