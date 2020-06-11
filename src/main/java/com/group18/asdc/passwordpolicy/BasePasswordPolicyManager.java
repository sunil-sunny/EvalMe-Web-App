package com.group18.asdc.passwordpolicy;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.errorhandling.PasswordPolicyException;

public class BasePasswordPolicyManager implements IBasePasswordPolicyManager {

    private static final HashMap<String, String> VALUE_VS_CLASSNAME_MAP = new HashMap<String, String>();
    private static ArrayList<String> enabledPasswordPolicies = null;

    static {
        // value and their class name/ package name
        VALUE_VS_CLASSNAME_MAP.put("MinLength", "com.group18.asdc.passwordpolicy.MinlengthPolicy");
        VALUE_VS_CLASSNAME_MAP.put("MaxLength", "");

        //
    }

    public BasePasswordPolicyManager() {
        // load default configurations
        loadDefaultConfigurations();
    }

    private void loadDefaultConfigurations() {
        //
        if (enabledPasswordPolicies == null) {

            IPasswordPolicyDB passwordPolicyDB = SystemConfig.getSingletonInstance().getPasswordPolicyDB();
            enabledPasswordPolicies = passwordPolicyDB.loadBasePoliciesFromDB();

        }
        //
    }

    @Override
    public void validatePassword(String password) throws PasswordPolicyException {

        IBasePasswordPolicy passwordPolicy = null;
        for (String eachClass : enabledPasswordPolicies) {
        //
            try {
                //
                passwordPolicy = (IBasePasswordPolicy) Class.forName(VALUE_VS_CLASSNAME_MAP.get(eachClass))
                        .getConstructor().newInstance();
                passwordPolicy.validate(password);
                //
            }
            catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException
                    | ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

}