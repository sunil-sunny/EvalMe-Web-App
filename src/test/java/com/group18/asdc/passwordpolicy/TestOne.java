package com.group18.asdc.passwordpolicy;

import com.group18.asdc.errorhandling.PasswordPolicyException;

public class TestOne implements IBasePasswordPolicy {

    public TestOne()
    {
        System.out.println("rrrrrrrrrrrrrr");
    }

    public TestOne(Integer value) {
        System.out.println("sssssssss" + value);
    }

    public void print() {
        System.out.println("qqqqqqqqqqqqqq");
    }

    @Override
    public void validate(String password) throws PasswordPolicyException {
        System.out.println("ttttttttttttttt");
    }
    
}