package com.group18.asdc.database;

public enum SQLQueries {

	USER_AUTH_BY_EMAIL_PASSWORD("select * from user where bannerid = ? and password = ?"),
	GET_BASEPASSWORD_POLICIES("select * from BasePasswordPolicyConfig where IS_ENABLED = true"),
	GET_HISTORYPASSWORD_POLICIES("select * from HistoryPasswordPolicyConfig where IS_ENABLED = true");

	private final String sqlQuery;

	private SQLQueries(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

	@Override
	public String toString() {
		return sqlQuery;
	}
}