/*
 * package com.group18.asdc.service;
 * 
 * import static org.junit.Assert.assertEquals; import static
 * org.mockito.ArgumentMatchers.any; import static
 * org.mockito.ArgumentMatchers.isA; import static org.mockito.Mockito.doAnswer;
 * import static org.mockito.Mockito.doNothing; import static
 * org.mockito.Mockito.times; import static org.mockito.Mockito.verify; import
 * static org.mockito.Mockito.when;
 * 
 * import java.sql.SQLException; import java.util.ArrayList;
 * 
 * import com.group18.asdc.dao.UserDao; import com.group18.asdc.entities.User;
 * import com.group18.asdc.service.UserService; import
 * com.group18.asdc.service.UserServiceImpl; import
 * com.group18.asdc.util.CommonUtil;
 * 
 * import org.junit.Before; import org.junit.Test; import
 * org.junit.runner.RunWith; import org.mockito.InjectMocks; import
 * org.mockito.Mock; import org.mockito.MockitoAnnotations; import
 * org.mockito.junit.MockitoJUnitRunner; import
 * org.springframework.boot.test.context.TestConfiguration; import
 * org.springframework.context.annotation.Bean;
 * 
 * 
 * @RunWith(MockitoJUnitRunner.class) public class UserServiceTest {
 * 
 * @InjectMocks UserService userService = new UserServiceImpl();
 * 
 * @Mock UserDao userDao;
 * 
 * @Before public void init() { MockitoAnnotations.initMocks(this); }
 * 
 * private User getDefaultUserObj() { final User userObj = new User(); //
 * userObj.setBannerId("B00838575"); userObj.setEmail("kr630601@dal.ca");
 * userObj.setFirstName("Karthikk"); userObj.setLastName("Tamil");
 * userObj.setPassword("karthikk");
 * 
 * return userObj; }
 * 
 * @Test public void loadUserWithBannerIdTest() { User userObj = new User(); //
 * try { doAnswer(invocation -> { ArrayList arg0 = invocation.getArgument(0);
 * User arg1 = invocation.getArgument(1);
 * 
 * arg1 = getDefaultUserObj(); assertEquals("B00838575", arg0.get(0)); return
 * null; }).when(userDao).loadUserWithBannerId(isA(ArrayList.class),
 * isA(User.class)); } catch (SQLException e) { e.printStackTrace(); }
 * 
 * String bannerId = "B00838575"; userService.loadUserWithBannerId(bannerId,
 * userObj); // ArrayList checkList = new ArrayList<>();
 * checkList.add(bannerId); try { verify(userDao,
 * times(1)).loadUserWithBannerId(checkList, userObj); } catch (SQLException e)
 * { e.printStackTrace(); } ; }
 * 
 * @Test public void checkUpdatePassword() { try {
 * when(userDao.updatePassword(isA(ArrayList.class),
 * isA(ArrayList.class))).thenReturn(Boolean.TRUE); } catch (SQLException e) {
 * e.printStackTrace(); }
 * 
 * User userObj = getDefaultUserObj(); // assertEquals(Boolean.TRUE,
 * userService.updatePassword(userObj)); // ArrayList<Object> criteriaList = new
 * ArrayList<Object>(); criteriaList.add("B00838575"); ArrayList<Object>
 * valuesList = new ArrayList<Object>(); valuesList.add("karthikk"); // try {
 * verify(userDao, times(1)).updatePassword(criteriaList, valuesList); } catch
 * (SQLException e) { e.printStackTrace(); } }
 * 
 * @Test public void checkErrorUpdatePassword() { try {
 * when(userDao.updatePassword(isA(ArrayList.class),
 * isA(ArrayList.class))).thenThrow(new SQLException()); } catch (SQLException
 * e) { e.printStackTrace(); }
 * 
 * User userObj = getDefaultUserObj(); // assertEquals(Boolean.FALSE,
 * userService.updatePassword(userObj));
 * 
 * }
 * 
 * }
 */