package com.group18.asdc.passwordpolicy;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.errorhandling.PasswordPolicyException;
import com.group18.asdc.util.ICustomStringUtils;

public class BasePasswordPolicyManager implements IBasePasswordPolicyManager {

    // private static HashMap<String, String> VALUE_VS_CLASSNAMES_MAP = new
    // HashMap<String, String>();
    private ArrayList<HashMap> enabledPasswordPolicies = null;
    private IPasswordPolicyDB passwordPolicyDB = null;

    // static {
    // VALUE_VS_CLASSNAMES_MAP.put("MinLength", MinlengthPolicy.class.getName());
    // VALUE_VS_CLASSNAMES_MAP.put("MaxLength", MaxlengthPolicy.class.getName());
    // }

    public BasePasswordPolicyManager(IPasswordPolicyDB passwordPolicyDB) {
        // load default configurations
        this.passwordPolicyDB = passwordPolicyDB;
    }

    private void loadDefaultConfigurations() {
        //
        if (enabledPasswordPolicies == null) {
            System.out.println("DB called");
            enabledPasswordPolicies = passwordPolicyDB.loadBasePoliciesFromDB();
        }
        //
    }

    @Override
    public void validatePassword(String password) throws PasswordPolicyException {

        loadDefaultConfigurations();

        IBasePasswordPolicy passwordPolicy = null;
        ICustomStringUtils customStringUtils = SystemConfig.getSingletonInstance().getCustomStringUtils();
        // for (String eachClass : enabledPasswordPolicies) {
        // //
        // try {
        //
        // passwordPolicy = (IBasePasswordPolicy)
        // Class.forName(VALUE_VS_CLASSNAMES_MAP.get(eachClass))
        // .getConstructor().newInstance();
        // passwordPolicy.validate(password);
        // } catch (InstantiationException | IllegalAccessException |
        // IllegalArgumentException
        // | InvocationTargetException | NoSuchMethodException | SecurityException
        // | ClassNotFoundException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        //
        for (HashMap eachEnabledPolicy : enabledPasswordPolicies) {
            //
            String policyName = (String) eachEnabledPolicy.get("POLICY_NAME");
            String policyValue = (String) eachEnabledPolicy.get("POLICY_VALUE");
            if (policyName.equals("MinLength")) {
                passwordPolicy = new MinlengthPolicy(policyValue);
            } else if (policyName.equals("MaxLength")) {
                passwordPolicy = new MaxlengthPolicy(policyValue);
            } else if (policyName.equals("MinLowercase")) {
                passwordPolicy = new MinLowercasePolicy(policyValue, customStringUtils);
            } else if (policyName.equals("MinUppercase")) {
                passwordPolicy = new MinUppercasePolicy(policyValue, customStringUtils);
            } else if (policyName.equals("MinSpecialCharacter")) {
                passwordPolicy = new MinSpecialcharPolicy(policyValue, customStringUtils);
            } else if (policyName.equals("CharactersNotAllowed")) {
                passwordPolicy = new CharsNotAllowedPolicy(policyValue, customStringUtils);
            }
            //
            passwordPolicy.validate(password);

        }

    }

}