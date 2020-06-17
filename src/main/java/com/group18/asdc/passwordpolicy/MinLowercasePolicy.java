package com.group18.asdc.passwordpolicy;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.errorhandling.PasswordPolicyException;
import com.group18.asdc.util.ICustomStringUtils;

public class MinLowercasePolicy implements IBasePasswordPolicy {

    private Integer minLowerCase = null;
    private ICustomStringUtils customStringUtils = null;

    public MinLowercasePolicy() {

    }

    // public MinLowercasePolicy(Object minLowerCase){
    // this.minLowerCase = (Integer) minLowerCase;

    // }

    public MinLowercasePolicy(String minLowerCase, ICustomStringUtils customStringUtils) {
        // this(minLowerCase);
        this.minLowerCase = Integer.parseInt(minLowerCase);
        this.customStringUtils = customStringUtils;
    }

    @Override
    public void validate(String password) throws PasswordPolicyException {
        // customStringUtils = customStringUtils == null ?
        // SystemConfig.getSingletonInstance().getCustomStringUtils() :
        // customStringUtils;
        Integer lowerCaseCharsCount = customStringUtils.getLowerCaseCharactersCount(password);
        if (lowerCaseCharsCount < this.minLowerCase) {
            throw new PasswordPolicyException(
                    "Password does not contain " + minLowerCase + " of lower case characters");
        }
    }

}