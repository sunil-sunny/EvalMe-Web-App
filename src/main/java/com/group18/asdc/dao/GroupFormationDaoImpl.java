package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import com.group18.asdc.database.ConnectionManager;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.SurveyGroups;
import com.group18.asdc.util.GroupFormationDataBaseQueries;

public class GroupFormationDaoImpl implements GroupFormationDao{
	
	private final Logger log = Logger.getLogger(GroupFormationDaoImpl.class.getName());

	@Override
	public SurveyGroups getGroupFormationResults(Course course) {
		
		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		ResultSet theResultSet = null;
		
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection.prepareStatement(GroupFormationDataBaseQueries.GET_SURVEY_GROUPS.toString());
			thePreparedStatement.setInt(1, course.getCourseId());
			theResultSet = thePreparedStatement.executeQuery();
			
		} catch (SQLException e) {
			log.severe("SQL Exception while fetching Group Formation Results");
		} finally {
			try {
				if (null != theResultSet) {
					theResultSet.close();
				}
				if (null != connection) {
					connection.close();
				}
				if (null != thePreparedStatement) {
					thePreparedStatement.close();
				}
				log.info("Closing connection after fetching Group Formation Results");
			} catch (SQLException e) {
				log.severe("SQL Exception while closing the connection and statement after fetching Group Formation Results");
			}
		}

		return null;
	}

}
