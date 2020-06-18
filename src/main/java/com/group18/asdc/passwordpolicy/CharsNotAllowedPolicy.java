package com.group18.asdc.passwordpolicy;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.errorhandling.PasswordPolicyException;
import com.group18.asdc.util.ICustomStringUtils;

import org.apache.commons.lang3.StringUtils;

public class CharsNotAllowedPolicy implements IBasePasswordPolicy {

    private String charsNotAllowed = null;
    private ICustomStringUtils customStringUtils = null;

    public CharsNotAllowedPolicy() {

    }

    // public CharsNotAllowedPolicy(Object charsNotAllowed){
    // this.charsNotAllowed = charsNotAllowed.toString();
    // }

    public CharsNotAllowedPolicy(String charsNotAllowed, ICustomStringUtils customStringUtils) {
        this.charsNotAllowed = charsNotAllowed;
        this.customStringUtils = customStringUtils;
    }

    @Override
    public void validate(String password) throws PasswordPolicyException {
        // customStringUtils = customStringUtils == null ?
        // SystemConfig.getSingletonInstance().getCustomStringUtils() :
        // customStringUtils;

        //
        if (customStringUtils.containsAnyCharacter(password, this.charsNotAllowed)) {
            throw new PasswordPolicyException("Password contains restricted chars:" + charsNotAllowed);
        }
    }

}