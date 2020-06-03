package com.group18.asdc.security;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.group18.asdc.database.SQLQueries;
import com.group18.asdc.util.CommonUtil;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/public/**", "/forgot-password", "/registration", "/home", "/resetPassword").permitAll();

		http.authorizeRequests().antMatchers("/instructedcourses").hasRole("INSTRUCTOR").antMatchers("/enrolledcourses")
				.hasRole("STUDENT").antMatchers("/tacourses").hasRole("TA").antMatchers("/instructedcourses")
				.hasRole("INSTRUCTOR").antMatchers("/coursepage").hasRole("STUDENT")
				.antMatchers("/coursepageInstrcutor").hasAnyRole("INSTRUCTOR", "TA").antMatchers("/allocateTA")
				.hasAnyRole("INSTRUCTOR", "TA").antMatchers("/uploadstudents").hasAnyRole("INSTRUCTOR", "TA");
		http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login")
				.failureUrl("/login-error").defaultSuccessUrl("/login-success").permitAll();
		// .and()
		// .logout()
		// .deleteCookies("JSESSIONID")
		// .and()
		// .httpBasic();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(CommonUtil.getInstance().passwordEncoder())
				.usersByUsernameQuery(SQLQueries.USER_AUTH_BY_EMAIL.toString())
				.authoritiesByUsernameQuery(SQLQueries.GET_USER_ROLES.toString());
		// auth.inMemoryAuthentication()
		// .withUser("rob").password("{noop}rob").roles("ADMIN").and()
		// .withUser("student").password("{noop}student").roles("GUEST");
		// .passwordEncoder(passwordEncoder());

	}

}