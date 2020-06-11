package com.group18.asdc.passwordpolicy;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.errorhandling.PasswordPolicyException;

public class PasswordPolicyManager extends BasePasswordPolicyManager implements IPasswordPolicyManager {

    private static final HashMap<String, String> VALUE_VS_CLASSNAME_MAP = new HashMap<>();
    private static ArrayList<String> enabledPasswordPolicies = null;

    static {
        // value and their class name/ package name

    }

    public PasswordPolicyManager() {
        // load default configurations
        super();
        loadDefaultConfigurations();
    }

    private void loadDefaultConfigurations() {
        //
        if (enabledPasswordPolicies == null) {

            IPasswordPolicyDB passwordPolicyDB = SystemConfig.getSingletonInstance().getPasswordPolicyDB();
            enabledPasswordPolicies = passwordPolicyDB.loadPoliciesFromDB();

        }
        //
    }

    @Override
    public void validatePassword(String bannerId, String password) throws PasswordPolicyException {
        //
        super.validatePassword(password);
        //
        IPasswordPolicy passwordPolicy = null;
        for (String eachClass : enabledPasswordPolicies) {
            //
            try {

                passwordPolicy = (IPasswordPolicy) Class.forName(VALUE_VS_CLASSNAME_MAP.get(eachClass)).getConstructor()
                        .newInstance();
                passwordPolicy.validate(bannerId , password);

            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException
                    | ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
        //
    }
    
}