/*
 * package com.group18.asdc.util;
 * 
 * import static org.junit.jupiter.api.Assertions.assertEquals;
 * 
 * import java.util.ArrayList;
 * 
 * import org.junit.Before; import org.junit.Test; import
 * org.junit.runner.RunWith; import org.mockito.InjectMocks; import
 * org.mockito.MockitoAnnotations; import org.mockito.junit.MockitoJUnitRunner;
 * 
 * @RunWith(MockitoJUnitRunner.class) public class CommonUtilTest {
 * 
 * @InjectMocks CommonUtil commonUtil;
 * 
 * @Before public void init() { MockitoAnnotations.initMocks(this); }
 * 
 * @Test public void convertQueryToArrayListTest() { String email = "email",
 * password = "password" , query = "query"; // assertEquals( ArrayList.class
 * ,commonUtil.getInstance().convertQueryVariablesToArrayList(email,password,
 * query).getClass());
 * assertEquals(3,commonUtil.getInstance().convertQueryVariablesToArrayList(
 * email,password,query).size());
 * assertEquals(2,commonUtil.getInstance().convertQueryVariablesToArrayList(
 * email,password).size());
 * assertEquals(1,commonUtil.getInstance().convertQueryVariablesToArrayList(
 * email).size()); }
 * 
 * @Test public void generateResetPasswordTest() { assertEquals( String.class
 * ,commonUtil.getInstance().generateResetPassword().getClass()); }
 * 
 * }
 */