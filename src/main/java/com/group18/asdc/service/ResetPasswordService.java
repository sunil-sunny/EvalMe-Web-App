package com.group18.asdc.service;

import java.util.HashMap;

import com.group18.asdc.passwordpolicy.IPasswordPolicyManager;
import com.group18.asdc.security.IPasswordEncryption;

public interface ResetPasswordService {

    public HashMap resetPassword(UserService userService, String bannerId, String password,
            PasswordHistoryService passwordHistoryService, IPasswordPolicyManager passwordPolicyManager,
            IPasswordEncryption passwordEncryption);

}