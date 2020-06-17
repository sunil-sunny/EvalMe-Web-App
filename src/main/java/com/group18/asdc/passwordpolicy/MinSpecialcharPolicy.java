package com.group18.asdc.passwordpolicy;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.errorhandling.PasswordPolicyException;

public class MinSpecialcharPolicy implements IBasePasswordPolicy {

    private Integer minSpecialChars = null;
    public MinSpecialcharPolicy()
    {

    }

    public MinSpecialcharPolicy(Object minSpecialChars)
    {
        this.minSpecialChars = (Integer) minSpecialChars;
    }

    @Override
    public void validate(String password) throws PasswordPolicyException {
        Integer numberSpecialCharacters = SystemConfig.getSingletonInstance().getCustomStringUtils().getSpecialCharactersCount(password);
    
        if( numberSpecialCharacters < minSpecialChars )
        {
            throw new PasswordPolicyException("Password does not contain "+minSpecialChars+" of special characters.");
        } 
        

    }
    
}