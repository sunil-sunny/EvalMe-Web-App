package com.group18.asdc.passwordpolicy;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.errorhandling.PasswordPolicyException;

import org.apache.commons.lang3.StringUtils;


public class CharsNotAllowedPolicy implements IBasePasswordPolicy {

    private String charsNotAllowed = null;

    public CharsNotAllowedPolicy(){

    }

    public CharsNotAllowedPolicy(Object charsNotAllowed){
        this.charsNotAllowed = charsNotAllowed.toString();
    }

    @Override
    public void validate(String password) throws PasswordPolicyException {
        //
        if (SystemConfig.getSingletonInstance().getCustomStringUtils().containsAnyCharacter(password, this.charsNotAllowed))
        {
            throw new PasswordPolicyException("Password contains restricted chars:"+charsNotAllowed);
        }
    }
    
}