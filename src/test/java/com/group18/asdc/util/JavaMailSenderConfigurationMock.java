package com.group18.asdc.util;

public class JavaMailSenderConfigurationMock implements IJavaMailSenderConfiguration {

    @Override
    public String getEmail() {
        // TODO Auto-generated method stub
        return "abcd@gmail.com";
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return "password";
    }
    
}