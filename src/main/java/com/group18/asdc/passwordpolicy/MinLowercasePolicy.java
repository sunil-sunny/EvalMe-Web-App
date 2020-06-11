package com.group18.asdc.passwordpolicy;

import com.group18.asdc.errorhandling.PasswordPolicyException;

public class MinLowercasePolicy implements IBasePasswordPolicy {

    public MinLowercasePolicy()
    {

    }

    public MinLowercasePolicy(Object minLowerCases){

    }

    
    @Override
    public void validate(String password) throws PasswordPolicyException {
        // TODO Auto-generated method stub

    }
    
}