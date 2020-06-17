package com.group18.asdc.passwordpolicy;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.errorhandling.PasswordPolicyException;

public class BasePasswordPolicyManager implements IBasePasswordPolicyManager {

    private static HashMap<String, String> VALUE_VS_CLASSNAMES_MAP = new HashMap<String, String>();
    private static ArrayList<String> enabledPasswordPolicies = null;

    static {
        VALUE_VS_CLASSNAMES_MAP.put("MinLength", MinlengthPolicy.class.getName());
        VALUE_VS_CLASSNAMES_MAP.put("MaxLength", MaxlengthPolicy.class.getName());
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

    // private void createPolicyObjects() {

    // }

    // private void loadPolicyObjects() {
    // VALUE_VS_OBJECT_MAP.put("MinLength", getMinLengthPolicyInstance());
    // // VALUE_VS_OBJECT_MAP.put("MaxLength", "");

    // }

    @Override
    public void validatePassword(String password) throws PasswordPolicyException {

        IBasePasswordPolicy passwordPolicy = null;
        for (String eachClass : enabledPasswordPolicies) {
            //
            try {
                passwordPolicy = (IBasePasswordPolicy) Class.forName(VALUE_VS_CLASSNAMES_MAP.get(eachClass))
                        .getConstructor().newInstance();
                passwordPolicy.validate(password);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException
                    | ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //

        }

    }

}