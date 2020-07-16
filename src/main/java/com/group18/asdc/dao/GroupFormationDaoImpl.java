package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.database.ConnectionManager;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.Group;
import com.group18.asdc.entities.SurveyGroups;
import com.group18.asdc.entities.User;
import com.group18.asdc.util.GroupFormationDataBaseQueries;
import com.group18.asdc.util.QuestionManagerDataBaseQueries;

public class GroupFormationDaoImpl implements GroupFormationDao {

	private final Logger log = Logger.getLogger(GroupFormationDaoImpl.class.getName());

	@Override
	public SurveyGroups getGroupFormationResults(Course course) {

		SurveyGroups theSurveyGroup = null;

		try (Connection connection = ConnectionManager.getInstance().getDBConnection();
				PreparedStatement thePreparedStatementGetSurvey = connection
						.prepareStatement(GroupFormationDataBaseQueries.GET_SURVEY_GROUPS.toString());
				PreparedStatement thePreparedStatementGetGroup = connection
						.prepareStatement(GroupFormationDataBaseQueries.GET_GROUP_MEMBERS.toString());){

			theSurveyGroup = SystemConfig.getSingletonInstance().getModelAbstractFactory().getSurveyGroups();
			Group group = null;
			User user = null;
			List<Group> surveyGroups = new ArrayList<Group>();
			List<User> groupMembers = new ArrayList<User>();

			thePreparedStatementGetSurvey.setInt(1, course.getCourseId());
			ResultSet theResultSet = thePreparedStatementGetSurvey.executeQuery();

			while (theResultSet.next()) {
				theSurveyGroup.setSurveyId(theResultSet.getInt(1));
				group = SystemConfig.getSingletonInstance().getModelAbstractFactory().getGroup();
				group.setGroupId(theResultSet.getInt(3));
				group.setGroupName(theResultSet.getString(4));
				surveyGroups.add(group);
			}
			theSurveyGroup.setSurveyGroups(surveyGroups);

			for (int i = 0; i < surveyGroups.size(); i++) {

				thePreparedStatementGetGroup.setInt(1, surveyGroups.get(i).getGroupId());
				theResultSet = thePreparedStatementGetGroup.executeQuery();

				while (theResultSet.next()) {
					user = SystemConfig.getSingletonInstance().getModelAbstractFactory().getUser();
					user.setBannerId(theResultSet.getString(2));
					user.setFirstName(theResultSet.getString(3));
					user.setLastName(theResultSet.getString(4));
					user.setEmail(theResultSet.getString(5));
					groupMembers.add(user);
				}
				theSurveyGroup.getSurveyGroups().get(i).setGroupMembers(groupMembers);
			}
		} catch (SQLException e) {
			log.severe("SQL Exception while fetching Group Formation Results");
		} 
		return theSurveyGroup;
	}
}
