package com.group18.asdc.passwordpolicy;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.errorhandling.PasswordPolicyException;

public class MinLowercasePolicy implements IBasePasswordPolicy {

    private Integer minLowerCase = null;

    public MinLowercasePolicy()
    {

    }

    public MinLowercasePolicy(Object minLowerCase){
        this.minLowerCase = (Integer) minLowerCase;

    }
  
    @Override
    public void validate(String password) throws PasswordPolicyException {
        Integer lowerCaseCharsCount = SystemConfig.getSingletonInstance().getCustomStringUtils().getLowerCaseCharactersCount(password);
        if( lowerCaseCharsCount < this.minLowerCase )
        {
            throw new PasswordPolicyException("Password does not contain "+minLowerCase+" of lower case characters");
        }
    }
    
}