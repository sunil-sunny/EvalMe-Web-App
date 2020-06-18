package com.group18.asdc.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.dao.UserDao;
import com.group18.asdc.entities.User;
import com.group18.asdc.errorhandling.PasswordPolicyException;
import com.group18.asdc.passwordpolicy.MinlengthPolicy;
import com.group18.asdc.passwordpolicy.BasePasswordPolicyManagerMock;
import com.group18.asdc.passwordpolicy.TestOne;
import com.group18.asdc.util.IQueryVariableToArrayList;
import com.group18.asdc.util.QueryVariableToArraylist;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Mock
    IQueryVariableToArrayList queryVariableToArraylist;

    @InjectMocks
    UserService userService = new UserServiceImpl(queryVariableToArraylist);

    @Mock
    UserDao userDao;

    @Mock
    SystemConfig systemConfig;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        
    }

    private User getDefaultUserObj() {

        User userObj = new User();
        //
        userObj.setBannerId("B00838575");
        userObj.setEmail("kr630601@dal.ca");
        userObj.setFirstName("Karthikk");
        userObj.setLastName("Tamil");
        userObj.setPassword("karthikk");

        return userObj;

    }

    private void getDefaultUserObj(User userObj) {

        userObj.setBannerId("B00838575");
        userObj.setEmail("kr630601@dal.ca");
        userObj.setFirstName("Karthikk");
        userObj.setLastName("Tamil");
        userObj.setPassword("karthikk");
    }

    @Test
    public void loadUserWithBannerIdTest() {
        User userObj = new User();
        //
        ArrayList valuesList = new ArrayList<>();
        valuesList.add("B00838575");
        
        when(queryVariableToArraylist.convertQueryVariablesToArrayList(isA(String.class))).thenReturn(valuesList);
        doAnswer(invocation -> {
            ArrayList arg0 = invocation.getArgument(0);
            User arg1 = invocation.getArgument(1);

            getDefaultUserObj(arg1);
            assertEquals("B00838575", arg0.get(0));
            return null;
        }).when(userDao).loadUserWithBannerId(isA(ArrayList.class), isA(User.class));

        String bannerId = "B00838575";
        userService.loadUserWithBannerId(bannerId, userObj);
        //
        assertEquals("B00838575", userObj.getBannerId());
        assertEquals("kr630601@dal.ca", userObj.getEmail());
        assertEquals("Karthikk", userObj.getFirstName());
        assertEquals("Tamil", userObj.getLastName());
        ArrayList checkList = new ArrayList<>();
        checkList.add(bannerId);

        verify(userDao, times(1)).loadUserWithBannerId(checkList, userObj);

        ;
    }

    @Test
    public void loadUserWithBannerIdUnavailableTest() {
        User userObj = new User();
        //
        ArrayList valuesList = new ArrayList<>();
        valuesList.add("B00838575");
        
        when(queryVariableToArraylist.convertQueryVariablesToArrayList(isA(String.class))).thenReturn(valuesList);
        doAnswer(invocation -> {
            ArrayList arg0 = invocation.getArgument(0);
            User arg1 = invocation.getArgument(1);
            assertEquals("B00838575", arg0.get(0));
            return null;
        }).when(userDao).loadUserWithBannerId(isA(ArrayList.class), isA(User.class));

        String bannerId = "B00838575";
        userService.loadUserWithBannerId(bannerId, userObj);
        //
        assertNull(userObj.getEmail());
        assertNull(userObj.getBannerId());
        ArrayList checkList = new ArrayList<>();
        checkList.add(bannerId);
        verify(userDao, times(1)).loadUserWithBannerId(checkList, userObj);
        ;
    }

    // @Test
    // public void checkUpdatePassword() {

    //     ArrayList<Object> criteriaList = new ArrayList<Object>();
    //     criteriaList.add("B00838575");
    //     ArrayList<Object> valuesList = new ArrayList<Object>();
    //     valuesList.add("karthikk");
    //     when(queryVariableToArraylist.convertQueryVariablesToArrayList(is(ArrayList.class), isA(ArrayList.class))).thenReturn(valuesList);

    //     when(userDao.updatePassword(isA(ArrayList.class), isA(ArrayList.class))).thenReturn(Boolean.TRUE);

    //     User userObj = getDefaultUserObj();
    //     //
    //     assertEquals(Boolean.TRUE, userService.updatePassword(userObj));
    //     //
       
    //     //
    //     verify(userDao, times(1)).updatePassword(criteriaList, valuesList);
    // }


}