package com.group18.asdc.controller;

import javax.servlet.http.HttpSession;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.entities.PasswordHistory;
import com.group18.asdc.entities.User;
import com.group18.asdc.errorhandling.PasswordPolicyException;
import com.group18.asdc.handlingformsubmission.ResetPassword;
import com.group18.asdc.passwordpolicy.BasePasswordPolicyManager;
import com.group18.asdc.passwordpolicy.PasswordPolicyManager;
import com.group18.asdc.security.IPasswordEncryption;
import com.group18.asdc.security.SecurityConfiguration;
import com.group18.asdc.service.EmailService;
import com.group18.asdc.service.PasswordHistoryService;
import com.group18.asdc.service.UserService;
import com.group18.asdc.util.CommonUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.group18.asdc.entities.User;
import com.group18.asdc.handlingformsubmission.ResetPassword;
import com.group18.asdc.service.EmailService;
import com.group18.asdc.service.UserService;
import com.group18.asdc.util.CommonUtil;

@Controller
public class LoginController {

  @RequestMapping("/")
  public RedirectView redirectPage() {
    return new RedirectView("/login-success");
  }

  @RequestMapping(value = { "/login", "/home" })
  public String login() {

    return "login.html";

  }

  // Login form with error
  @RequestMapping("/login-error")
  public String loginError(Model model) {

    model.addAttribute("loginError", Boolean.TRUE);
    return "login.html";

  }

  @RequestMapping("/login-success")
  public RedirectView loginSuccess(Authentication authentication) {
    String redirectURL = CommonUtil.roleVsLandingPage.get(authentication.getAuthorities().iterator().next().toString());
    return new RedirectView(redirectURL);

  }

  @RequestMapping("/forgot-password")
  public String forgotPasswordPage() {

    return "forgot-password.html";

  }

  @GetMapping("/resetPassword")
  public String sendResetRequest(@RequestParam(name = "username", required = true) String bannerId, Model model,
      HttpSession session) {
    //
    // get User object with BOO number value and send an email
    //
    UserService userService = SystemConfig.getSingletonInstance().getTheUserService();
    User userObj = new User(bannerId, userService);
    /*
    * 
    */
    if (userObj.getEmail() != null && !userObj.getEmail().isEmpty()) {
      String genPassword = SystemConfig.getSingletonInstance().getRandomStringGenerator().generateRandomString();
      session.setAttribute("RESET_PASSWORD", genPassword);
      model.addAttribute("resetForm", new ResetPassword(bannerId));
      model.addAttribute("sentEmail", userObj.getEmail());
      EmailService emailService = SystemConfig.getSingletonInstance().getTheEmailService();
      emailService.sendSimpleMessage(userObj.getEmail(), "Reset Password", "Your reset password is: " + genPassword);
      return "resetPassword.html";
    } else {
      model.addAttribute("BANNER_ID_NOT_EXIST", Boolean.TRUE);
      return "forgot-password.html";
    }
  }

  @PostMapping("/resetPassword")
  public String resetPassword(@ModelAttribute ResetPassword resetForm, Model model, HttpSession session) {

    String redirectURL = "login-success";
    Boolean isError = false;
    //
    UserService userService = SystemConfig.getSingletonInstance().getTheUserService();
    User userObj = new User(resetForm.getbannerId(), userService);
    //
    if (!resetForm.getgeneratedPassword().equals(session.getAttribute("RESET_PASSWORD"))) {
      model.addAttribute("genPasswordError", Boolean.TRUE);
      model.addAttribute("reason", "Password sent in mail does not match");
      isError = Boolean.TRUE;
    } else if (!resetForm.getnewPassword().equals(resetForm.getconfirmNewPassword())) {
      model.addAttribute("confirmPasswordError", Boolean.TRUE);
      model.addAttribute("reason", "New password and confirm password does not match");
      isError = Boolean.TRUE;
    } else {
      // set new password in the user model
      try {
        userObj.setPassword(resetForm.getconfirmNewPassword());
        userObj.isPasswordValid(SystemConfig.getSingletonInstance().getPasswordPolicyManager());
        if (!userService.updatePassword(userObj, SystemConfig.getSingletonInstance().getPasswordEncryption())) {
          model.addAttribute("passwordResetError", Boolean.TRUE);
          model.addAttribute("reason", "Error resetting password.");
          isError = Boolean.TRUE;
        } else {
          // insert value to history
          PasswordHistory passwordHistory = new PasswordHistory();
          passwordHistory.setBannerID(userObj.getBannerId());
          passwordHistory.setPassword(userObj.getPassword());
          passwordHistory.setDate(System.currentTimeMillis());
          //
          PasswordHistoryService passwordHistoryService = SystemConfig.getSingletonInstance()
              .getPasswordHistoryService();
          passwordHistoryService.insertPassword(passwordHistory,
              SystemConfig.getSingletonInstance().getPasswordEncryption());
        }

      } catch (PasswordPolicyException e) {
        model.addAttribute("historyConstrainError", Boolean.TRUE);
        model.addAttribute("reason", e.getMessage());
        isError = Boolean.TRUE;
      }
    }
    if (isError) {
      model.addAttribute("resetForm", new ResetPassword(resetForm.getbannerId()));
      model.addAttribute("isError", Boolean.TRUE);
      model.addAttribute("sentEmail", userObj.getEmail());
      return "resetPassword";
    } else {
      return "redirect:" + redirectURL;
    }

  }

  @GetMapping("/error")
  public String getErrorPage() {

    return "error";
  }

  @GetMapping("/resetPasswordPolicies")
  public String resetPasswordPolicies() {
    SystemConfig.getSingletonInstance().setBasePasswordPolicyManager(
        new BasePasswordPolicyManager(SystemConfig.getSingletonInstance().getPasswordPolicyDB()));
    SystemConfig.getSingletonInstance()
        .setPasswordPolicyManager(new PasswordPolicyManager(SystemConfig.getSingletonInstance().getPasswordPolicyDB()));
    return "policyReset";
  }

}