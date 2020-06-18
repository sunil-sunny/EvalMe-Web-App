package com.group18.asdc.util;

import java.util.Properties;

import org.springframework.mail.javamail.JavaMailSenderImpl;

public class DefaultMailSenderConfiguration implements IJavaMailSenderConfiguration {

    private String userName = System.getenv("MAIL_SENDER_NAME");
    private String password = System.getenv("MAIL_SENDER_PASSWORD");

    @Override
    public String getEmail() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    
}