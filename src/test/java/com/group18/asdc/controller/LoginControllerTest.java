package com.group18.asdc.controller;

import com.group18.asdc.EvalMeMain;
import com.group18.asdc.security.SecurityConfiguration;
import com.group18.asdc.service.EmailService;
import com.group18.asdc.service.EmailServiceImpl;
import com.group18.asdc.service.UserService;
import com.group18.asdc.service.test.UserServiceImplMock;
import com.group18.asdc.util.CommonUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;
    

    @MockBean
    EmailService emailService;

    @MockBean
    DataSource dataSource;

    @Mock
    CommonUtil commonUtil;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testHTMLCalls() throws Exception {
        mockMvc.perform(get("/coursepage")).andExpect(redirectedUrl("http://localhost/login"));

        mockMvc.perform(get("/adminpage")).andExpect(redirectedUrl("http://localhost/login"));

        mockMvc.perform(get("/login")).andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"));

        mockMvc.perform(get("/forgot-password")).andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"));

    }

    // @Test
    // public void resetPasswordTest() throws Exception
    // {
    //     emailService = new EmailServiceImpl();
    //     userService = new UserServiceImplMock();
    //     when(commonUtil.generateResetPassword()).thenReturn("HEY");

    //     mockMvc.perform(post("/resetPassword").param("username", "B00123456")).andExpect(status().isOk());

    //     verify(emailService,times(1)).sendSimpleMessage("", "", "");;
    // }


}