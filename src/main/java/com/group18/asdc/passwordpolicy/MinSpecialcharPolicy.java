package com.group18.asdc.passwordpolicy;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.errorhandling.PasswordPolicyException;
import com.group18.asdc.util.ICustomStringUtils;

public class MinSpecialcharPolicy implements IBasePasswordPolicy {

    private Integer minSpecialChars = null;
    private ICustomStringUtils customStringUtils = null;

    public MinSpecialcharPolicy() {

    }

    // public MinSpecialcharPolicy(Object minSpecialChars)
    // {
    // this.minSpecialChars = (Integer) minSpecialChars;
    // }

    public MinSpecialcharPolicy(String minSpecialChars, ICustomStringUtils customStringUtils) {
        // this(minSpecialChars);
        this.minSpecialChars = Integer.parseInt(minSpecialChars);
        this.customStringUtils = customStringUtils;
    }

    @Override
    public void validate(String password) throws PasswordPolicyException {
        //
        // customStringUtils = customStringUtils == null ?
        // SystemConfig.getSingletonInstance().getCustomStringUtils() :
        // customStringUtils;
        //
        Integer numberSpecialCharacters = customStringUtils.getSpecialCharactersCount(password);

        if (numberSpecialCharacters < minSpecialChars) {
            throw new PasswordPolicyException(
                    "Password does not contain " + minSpecialChars + " of special characters.");
        }

    }

}