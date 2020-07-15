package com.group18.asdc.service;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.group18.asdc.entities.PasswordHistory;
import com.group18.asdc.entities.User;
import com.group18.asdc.errorhandling.PasswordPolicyException;
import com.group18.asdc.passwordpolicy.IPasswordPolicyManager;
import com.group18.asdc.security.IPasswordEncryption;

public class ResetPasswordServiceImpl implements ResetPasswordService {

    private Logger logger = Logger.getLogger(ResetPasswordService.class.getName());

    @Override
    public HashMap resetPassword(UserService userService, String bannerId, String password,
            PasswordHistoryService passwordHistoryService, IPasswordPolicyManager passwordPolicyManager,
            IPasswordEncryption passwordEncryption) {

        HashMap<String, Object> resultMap = new HashMap<>();
        boolean isError = false;
        User userObj = new User();
        userService.loadUserWithBannerId(bannerId, userObj);
        if (userObj.isValidUser()) {
            try {
                resultMap.put("USER_EMAIL", userObj.getEmail());
                userObj.setPassword(password);
                userObj.validatePassword(passwordPolicyManager);
                logger.log(Level.INFO, "Updating the password for the user=" + userObj.getBannerId());
                if (userService.updatePassword(userObj, passwordEncryption)) {
                    PasswordHistory passwordHistory = new PasswordHistory();
                    passwordHistory.setBannerID(userObj.getBannerId());
                    passwordHistory.setPassword(userObj.getPassword());
                    passwordHistory.setDate(System.currentTimeMillis());
                    passwordHistoryService.insertPassword(passwordHistory, passwordEncryption);
                } else {
                    resultMap.put("REASON", "Error resetting password. Please try after some time");
                    isError = Boolean.TRUE;
                }
            } catch (PasswordPolicyException e) {
                logger.log(Level.SEVERE, "Error updating password for the user=" + bannerId + " password=" + password);
                resultMap.put("REASON", e.getMessage());
                isError = Boolean.TRUE;
            }
        } else {
            isError = Boolean.TRUE;
            resultMap.put("REASON", "DB Connection Error");
        }
        resultMap.put("IS_ERROR", isError);
        return resultMap;
    }

}