package com.group18.asdc.passwordpolicy;

import com.group18.asdc.errorhandling.PasswordPolicyException;

public class MinUppercasePolicy implements IBasePasswordPolicy {

    public MinUppercasePolicy()
    {

    }

    public MinUppercasePolicy(Object minUpperCases)
    {

    }
    
    @Override
    public void validate(String password) throws PasswordPolicyException {
        // TODO Auto-generated method stub

    }
    
}