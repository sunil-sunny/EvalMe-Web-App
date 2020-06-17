package com.group18.asdc.entities;

import com.group18.asdc.errorhandling.PasswordPolicyException;
import com.group18.asdc.passwordpolicy.BasePasswordPolicyManagerMock;
import com.group18.asdc.passwordpolicy.CharsNotAllowedPolicy;
import com.group18.asdc.passwordpolicy.MaxlengthPolicy;
import com.group18.asdc.passwordpolicy.MinLowercasePolicy;
import com.group18.asdc.passwordpolicy.MinSpecialcharPolicy;
import com.group18.asdc.passwordpolicy.MinUppercasePolicy;
import com.group18.asdc.passwordpolicy.MinlengthPolicy;
import com.group18.asdc.util.CustomStringUtils;
import com.group18.asdc.util.ICustomStringUtils;

import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

    @Mock
    ICustomStringUtils customStringUtils = new CustomStringUtils();

    //
    @Test(expected = PasswordPolicyException.class)
    public void validateMinLengthPasswordErrorTest() throws PasswordPolicyException
    {
        BasePasswordPolicyManagerMock obj = new BasePasswordPolicyManagerMock(new MinlengthPolicy("10"));
        //
        obj.validatePassword("karthikk");
    }

    @Test
    public void validateMinLengthPasswordTest() throws PasswordPolicyException
    {
        BasePasswordPolicyManagerMock obj = new BasePasswordPolicyManagerMock(new MinlengthPolicy("8"));
        //
        obj.validatePassword("karthikk");
    }


    // @Test(expected = PasswordPolicyException.class)
    // public void validateMinLengthPasswordDefaultTest() throws PasswordPolicyException
    // {
    //     BasePasswordPolicyManagerMock obj = new BasePasswordPolicyManagerMock(new MinlengthPolicy());
    //     //
    //     obj.validatePassword("1234567");
    // }


    @Test(expected = PasswordPolicyException.class)
    public void validateMaxLengthPasswordErrorTest() throws PasswordPolicyException
    {
        BasePasswordPolicyManagerMock obj = new BasePasswordPolicyManagerMock(new MaxlengthPolicy("15"));
        //
        obj.validatePassword("karthikkkarthikk");
    }

    @Test
    public void validateMaxLengthPasswordTest() throws PasswordPolicyException
    {
        BasePasswordPolicyManagerMock obj = new BasePasswordPolicyManagerMock(new MaxlengthPolicy("12"));
        //
        obj.validatePassword("karthikk");
    }


    // @Test(expected = PasswordPolicyException.class)
    // public void validateMaxLengthPasswordDefaultTest() throws PasswordPolicyException
    // {
    //     BasePasswordPolicyManagerMock obj = new BasePasswordPolicyManagerMock(new MaxlengthPolicy());
    //     //
    //     obj.validatePassword("karthikkkarthikk");
    // }

    @Test(expected = PasswordPolicyException.class)
    public void validateMinLengthUpperPasswordErrorTest() throws PasswordPolicyException
    {
        BasePasswordPolicyManagerMock obj = new BasePasswordPolicyManagerMock(new MinUppercasePolicy("5",customStringUtils));
        //
        obj.validatePassword("KArthikk");
    }

    @Test
    public void validateMinLengthUpperPasswordTest() throws PasswordPolicyException
    {
        BasePasswordPolicyManagerMock obj = new BasePasswordPolicyManagerMock(new MinUppercasePolicy("5",customStringUtils));
        //
        obj.validatePassword("KARTHIKK");
    }


    // @Test(expected = PasswordPolicyException.class)
    // public void validateMinLengthUpperPasswordDefaultTest() throws PasswordPolicyException
    // {
    //     BasePasswordPolicyManagerMock obj = new BasePasswordPolicyManagerMock(new MinUppercasePolicy());
    //     //
    //     obj.validatePassword("karthikk");
    // }

    @Test(expected = PasswordPolicyException.class)
    public void validateMinLengthlowerPasswordErrorTest() throws PasswordPolicyException
    {
        BasePasswordPolicyManagerMock obj = new BasePasswordPolicyManagerMock(new MinLowercasePolicy("8",customStringUtils));
        //
        obj.validatePassword("KARTHIKK");
    }

    @Test
    public void validateMinLengthlowerPasswordTest() throws PasswordPolicyException
    {
        BasePasswordPolicyManagerMock obj = new BasePasswordPolicyManagerMock(new MinLowercasePolicy("8",customStringUtils));
        //
        obj.validatePassword("karthikk");
    }


    // @Test(expected = PasswordPolicyException.class)
    // public void validateMinLengthlowerPasswordDefaultTest() throws PasswordPolicyException
    // {
    //     BasePasswordPolicyManagerMock obj = new BasePasswordPolicyManagerMock(new MinLowercasePolicy());
    //     //
    //     obj.validatePassword("karthIKK");
    // }

    @Test(expected = PasswordPolicyException.class)
    public void validateMinLengthSpecialCharsPasswordErrorTest() throws PasswordPolicyException
    {
        BasePasswordPolicyManagerMock obj = new BasePasswordPolicyManagerMock(new MinSpecialcharPolicy("3",customStringUtils));
        //
        obj.validatePassword("k@#arthikk");
    }

    @Test
    public void validateMinLengthSpecialCharsPasswordTest() throws PasswordPolicyException
    {
        BasePasswordPolicyManagerMock obj = new BasePasswordPolicyManagerMock(new MinSpecialcharPolicy("3",customStringUtils));
        //
        obj.validatePassword("k@#$arthikk");
    }


    // @Test
    // public void validateMinLengthCharsPasswordDefaultTest() throws PasswordPolicyException
    // {
    //     BasePasswordPolicyManagerMock obj = new BasePasswordPolicyManagerMock(new MinSpecialcharPolicy());
    //     //
    //     obj.validatePassword("k@#arthikk");
    // }

    @Test(expected = PasswordPolicyException.class)
    public void validateCharsNotAllowedPasswordTest() throws PasswordPolicyException
    {
        BasePasswordPolicyManagerMock obj = new BasePasswordPolicyManagerMock(new CharsNotAllowedPolicy("@!#",customStringUtils));
        //
        obj.validatePassword("kar@thikk");
    }

    @Test
    public void validateCharsNotAllowedPasswordErrorTest() throws PasswordPolicyException
    {
        BasePasswordPolicyManagerMock obj = new BasePasswordPolicyManagerMock(new CharsNotAllowedPolicy("@!#",customStringUtils));
        //
        obj.validatePassword("kar*()thikk");
    }
    
}