package com.group18.asdc.passwordpolicy;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.errorhandling.PasswordPolicyException;
import com.group18.asdc.util.ICustomStringUtils;

public class MinUppercasePolicy implements IBasePasswordPolicy {

    private Integer minUpperCase = null;
    private ICustomStringUtils customStringUtils = null;

    public MinUppercasePolicy() {

    }

    // public MinUppercasePolicy(Object minUpperCase)
    // {
    // this.minUpperCase = (Integer)minUpperCase;
    // }

    public MinUppercasePolicy(String minUpperCase, ICustomStringUtils customStringUtils) {
        // this(minUpperCase);
        this.minUpperCase = Integer.parseInt(minUpperCase);
        this.customStringUtils = customStringUtils;
    }

    @Override
    public void validate(String password) throws PasswordPolicyException {
        // customStringUtils = customStringUtils == null ?
        // SystemConfig.getSingletonInstance().getCustomStringUtils() :
        // customStringUtils;

        Integer upperCaseCharsCount = customStringUtils.getUpperCaseCharactersCount(password);
        if (upperCaseCharsCount < this.minUpperCase) {
            throw new PasswordPolicyException(
                    "Password does not contain " + minUpperCase + " of upper case characters");
        }
    }

}