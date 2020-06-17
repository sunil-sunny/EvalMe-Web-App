package com.group18.asdc.passwordpolicy;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.errorhandling.PasswordPolicyException;

public class MinUppercasePolicy implements IBasePasswordPolicy {

    private Integer minUpperCase = null;

    public MinUppercasePolicy()
    {

    }

    public MinUppercasePolicy(Object minUpperCase)
    {
        this.minUpperCase = (Integer)minUpperCase;
    }
    
    @Override
    public void validate(String password) throws PasswordPolicyException {
        Integer upperCaseCharsCount = SystemConfig.getSingletonInstance().getCustomStringUtils().getUpperCaseCharactersCount(password);
        if( upperCaseCharsCount < this.minUpperCase )
        {
            throw new PasswordPolicyException("Password does not contain "+minUpperCase+" of upper case characters");
        }
    }
    
}