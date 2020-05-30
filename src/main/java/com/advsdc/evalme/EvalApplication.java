package com.advsdc.evalme;

import java.sql.SQLException;
import java.util.ArrayList;

import com.advsdc.evalme.dbconnection.SQLMethods;
import com.advsdc.evalme.dbconnection.SQLQueries;
import com.advsdc.evalme.util.CommonUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@SpringBootApplication
@ComponentScan({"com.advsdc.evalme.dao,com.advsdc.evalme.dbconnection,com.advsdc.evalme.security"})
@Controller
public class EvalApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(EvalApplication.class, args);
	}


	@RequestMapping("/login.html")
  public String login() {

    return "login.html";

  }

  // Login form with error
  @RequestMapping("/login-error.html")
  public String loginError(Model model) {
    
    model.addAttribute("loginError", true);
    return "login.html";

  }

  @RequestMapping("/login-success")
  public RedirectView loginSuccess(Authentication authentication){

    String redirectURL = CommonUtil.roleVsLandingPage.get(authentication.getAuthorities().iterator().next().toString());
    return new RedirectView(redirectURL);

  }

  @RequestMapping("/forgot-password.html")
  public String forgotPasswordPage() {
    
    return "forgot-password.html";

  }

  @RequestMapping("")
  public String forgotPassword(){

    return null;
  }


}
