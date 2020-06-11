package com.group18.asdc.passwordpolicy;

import com.group18.asdc.errorhandling.PasswordPolicyException;

public class MinSpecialcharPolicy implements IBasePasswordPolicy {

    public MinSpecialcharPolicy()
    {

    }

    public MinSpecialcharPolicy(Object minSpecialChars)
    {
        
    }

    @Override
    public void validate(String password) throws PasswordPolicyException {
        // TODO Auto-generated method stub

    }
    
}