package com.group18.asdc.passwordpolicy;

import com.group18.asdc.errorhandling.PasswordPolicyException;

public class BasePasswordPolicyManagerMock implements IBasePasswordPolicyManager {

    private IBasePasswordPolicy passwordPolicyObj;

    public BasePasswordPolicyManagerMock(IBasePasswordPolicy passwordPolicyObj) {
        this.passwordPolicyObj = passwordPolicyObj;
    }

    @Override
    public void validatePassword(String password) throws PasswordPolicyException {
        //
        passwordPolicyObj.validate(password);
        // IPasswordPolicy classObj;
        
        // try {
        //     classObj = (IPasswordPolicy) Class.forName("com.group18.asdc.passwordpolicy.TestOne").newInstance();
        //     return classObj.validate(password);
        // } catch (InstantiationException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // } catch (IllegalAccessException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // } catch (ClassNotFoundException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        //     //
        // return null;

    }

}