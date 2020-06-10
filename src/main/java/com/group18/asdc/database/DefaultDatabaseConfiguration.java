package com.group18.asdc.database;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

public class DefaultDatabaseConfiguration implements IDatabaseConfiguration{


	//private String url = System.getenv("DB_URL");
	//private String username = System.getenv("DB_USERNAME");
	//private String password = System.getenv("DB_PASSWORD");
	private String url = "jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_18_DEVINT?useSSL=false&serverTimezone=UTC";
	private String username = "CSCI5308_18_DEVINT_USER";
	private String password = "CSCI5308_18_DEVINT_18000";


	@Override
	public String getDatabaseUserName() {
		return username;
	}

	@Override
	public String getDatabasePassword() {
		return password;
	}

	@Override
	public String getDatabaseURL() {
		return url;
	}

}