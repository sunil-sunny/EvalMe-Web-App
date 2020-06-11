package com.group18.asdc.passwordpolicy;

import com.group18.asdc.errorhandling.PasswordPolicyException;

public class MaxlengthPolicy implements IBasePasswordPolicy {

    public MaxlengthPolicy(){

    }

    public MaxlengthPolicy(Object maxLength){
        
    }

    @Override
    public void validate(String password) throws PasswordPolicyException {
        // TODO Auto-generated method stub

    }
    
}