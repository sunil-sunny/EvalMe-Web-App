package com.group18.asdc.database;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("spring.datasource")
public class DataSourceConnection {

	@Value("${spring.database.drivername}")
	private String driverClassName;
	@Value("${spring.database.dev.url}")
	private String url;
	@Value("${spring.database.dev.username}")
	private String username;
	@Value("${spring.database.dev.password}")
	private String password;

	@Profile("dev")
	@Bean
	public String devDatabaseConnection() {
		System.out.println("DB connection for DEV - H2");
		System.out.println(driverClassName);
		System.out.println(url);
		return "DB connection for DEV - H2";
	}

	@Profile("test")
	@Bean
	public String testDatabaseConnection() {
		System.out.println("DB Connection to RDS_TEST - Low Cost Instance");
		System.out.println(driverClassName);
		System.out.println(url);
		return "DB Connection to RDS_TEST - Low Cost Instance";
	}

	@Profile("prod")
	@Bean
	public String prodDatabaseConnection() {
		System.out.println("DB Connection to RDS_PROD - High Performance Instance");
		System.out.println(driverClassName);
		System.out.println(url);
		return "DB Connection to RDS_PROD - High Performance Instance";
	}

	@Bean
	public DataSource getDataSource() {
		
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
		dataSourceBuilder.url(url);
		dataSourceBuilder.username(username);
		dataSourceBuilder.password(password);
		return dataSourceBuilder.build();
	}

}