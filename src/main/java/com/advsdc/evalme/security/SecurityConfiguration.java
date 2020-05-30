package com.advsdc.evalme.security;

import javax.sql.DataSource;

import com.advsdc.evalme.dbconnection.SQLQueries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration  
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {  

    @Autowired
    private DataSource dataSource;
    
    @Override
    public void configure(HttpSecurity http) throws Exception {  

        http.authorizeRequests()
            .antMatchers( "/public/**","/forgot-password.html","/register","/home").permitAll()    
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login.html")
            .failureUrl("/login-error.html")
            .defaultSuccessUrl("/login-success")
            .permitAll();
            // .and()
            // .logout()
            // .deleteCookies("JSESSIONID")        
            // .and()
            // .httpBasic();  

    }  

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {  

        // auth.jdbcAuthentication()
        //     .dataSource(dataSource)
        //     // .passwordEncoder(passwordEncoder())
        //     .usersByUsernameQuery(SQLQueries.USER_AUTH_BY_EMAIL.toString());
        auth.inMemoryAuthentication() 
        .withUser("rob").password("{noop}rob").roles("ADMIN").and()
        .withUser("student").password("{noop}student").roles("GUEST");
        // .passwordEncoder(passwordEncoder());  

    }  

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }

}