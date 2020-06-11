package com.group18.asdc.passwordpolicy;

import com.group18.asdc.errorhandling.PasswordPolicyException;

public class CharsNotAllowedPolicy implements IBasePasswordPolicy {

    public CharsNotAllowedPolicy(){

    }

    public CharsNotAllowedPolicy(Object charsNotAllowed){
        
    }

    @Override
    public void validate(String password) throws PasswordPolicyException {
        // TODO Auto-generated method stub

    }
    
}