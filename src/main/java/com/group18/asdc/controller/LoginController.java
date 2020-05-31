package com.group18.asdc.controller;

import javax.servlet.http.HttpSession;

import com.group18.asdc.service.EmailService;
import com.group18.asdc.util.CommonUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {

  @Autowired
  public EmailService emailService;

  @RequestMapping("/login")
  public String login() {

    return "login.html";

  }

  // Login form with error
  @RequestMapping("/login-error")
  public String loginError(Model model) {

    model.addAttribute("loginError", true);
    return "login.html";

  }

  // @GetMapping("/greeting")
  // public String greetingForm(HttpSession session) {
  // System.out.println("wwwwwwwwwwwwwwwww" +
  // session.getAttribute("RESET_PASSWORD"));
  // return String.format("sssss");
  // }

  @RequestMapping("/login-success")
  public RedirectView loginSuccess(Authentication authentication) {

    // String generatedPassword = CommonUtil.getInstance().generateResetPassword();
    // session.setAttribute("RESET_PASSWORD", generatedPassword);
    // System.out.println("qqqqqqqqqqqqq"+generatedPassword);
    String redirectURL = CommonUtil.roleVsLandingPage.get(authentication.getAuthorities().iterator().next().toString());
    return new RedirectView(redirectURL);

  }

  @RequestMapping("/forgot-password")
  public String forgotPasswordPage() {

    return "forgot-password.html";

  }

  @RequestMapping("/sendResetRequest")
  public String sendResetRequest(@RequestParam(name = "name", required = true) String booNumber, HttpSession session) {
    //
    // get User object with BOO number value and send an email
    //

    /*
    * 
    */
    String generatedPassword = CommonUtil.getInstance().generateResetPassword();
    session.setAttribute("RESET_PASSWORD", generatedPassword);
    //
    // set generated password in
    //

    // emailService.sendSimpleMessage(to, subject, text);
    return null;
  }

}