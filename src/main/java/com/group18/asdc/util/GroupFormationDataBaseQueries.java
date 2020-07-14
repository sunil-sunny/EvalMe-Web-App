package com.group18.asdc.util;

public enum GroupFormationDataBaseQueries {
	
	GET_SURVEY_GROUPS("select r.surveyid, s.courseid, r.groupid, r.groupname from survey s, groupformationresults r where s.surveyid = r.surveyid and courseid=?;");
	
	private final String sqlQuery;

	private GroupFormationDataBaseQueries(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}
	
	@Override
	public String toString() {
		return sqlQuery;
	}

}
