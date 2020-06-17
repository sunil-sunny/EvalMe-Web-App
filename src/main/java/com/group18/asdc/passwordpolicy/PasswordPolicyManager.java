package com.group18.asdc.passwordpolicy;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.errorhandling.PasswordPolicyException;
import com.group18.asdc.service.PasswordHistoryService;

public class PasswordPolicyManager extends BasePasswordPolicyManager implements IPasswordPolicyManager {

    // private static final HashMap<String, String> VALUE_VS_CLASSNAMES_MAP = new
    // HashMap<>();
    private ArrayList<HashMap> enabledPasswordPolicies = null;
    private IPasswordPolicyDB passwordPolicyDB;

    // static {
    // // value and their class name/ package name
    // VALUE_VS_CLASSNAMES_MAP.put("HistoryConstraint",
    // HistoryConstraintPolicy.class.getName());

    // }

    public PasswordPolicyManager(IPasswordPolicyDB passwordPolicyDB) {
        // load default configurations
        super(passwordPolicyDB);
        this.passwordPolicyDB = passwordPolicyDB;
    }

    private void loadDefaultConfigurations() {
        //
        if (enabledPasswordPolicies == null) {
            enabledPasswordPolicies = passwordPolicyDB.loadPoliciesFromDB();
        }
        //
    }

    @Override
    public void validatePassword(String bannerId, String password) throws PasswordPolicyException {
        //
        loadDefaultConfigurations();
        super.validatePassword(password);
        //
        IPasswordPolicy passwordPolicy = null;
        //
        for (HashMap eachEnabledPolicy : enabledPasswordPolicies) {

            if (eachEnabledPolicy.get("POLICY_NAME").equals("HistoryConstraint")) {
                passwordPolicy = new HistoryConstraintPolicy((String) eachEnabledPolicy.get("POLICY_VALUE"),
                        SystemConfig.getSingletonInstance().getPasswordHistoryService(),
                        SystemConfig.getSingletonInstance().getPasswordEncryption());
            }
            //
            passwordPolicy.validate(bannerId, password);
        }

        // for (HashMap eachEnabledPolicy : enabledPasswordPolicies) {
        // //
        // try {

        // passwordPolicy = (IPasswordPolicy)
        // Class.forName(VALUE_VS_CLASSNAMES_MAP.get(eachEnabledPolicy.get("POLICY_NAME"))).getConstructor(Object.class)
        // .newInstance(eachEnabledPolicy.get("POLICY_VALUE"));
        // passwordPolicy.validate(bannerId , password);

        // } catch (InstantiationException | IllegalAccessException |
        // IllegalArgumentException
        // | InvocationTargetException | NoSuchMethodException | SecurityException
        // | ClassNotFoundException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }

        // }
        //
    }

}