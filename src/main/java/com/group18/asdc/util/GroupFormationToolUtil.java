package com.group18.asdc.util;

public class GroupFormationToolUtil {
	
	public final static String getAllCourses="SELECT * FROM course;";
	public final static String getCourseDetails="SELECT a.courseid,a.bannerid,b.rolename FROM courserole as a inner join role as b\r\n" + 
			"on a.roleid=b.roleid where a.courseid=?;";
	public final static String getUserById="SELECT * FROM user where bannerid=?;";
	
	public final static String emailRegex="^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@\"\r\n" + 
			"        + \"[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$";
	public final static String allocateTa="insert into courserole (roleid,courseid,bannerid) values (4,?,?);";
	public final static String getAlluserRelatedToCourse="SELECT c.bannerid,c.lastname,c.firstname,c.emailid FROM courserole as a\r\n" + 
			"inner join role as b on a.roleid=b.roleid\r\n" + 
			"inner join user as c on a.bannerid=c.bannerid where (b.rolename='STUDENT' || b.rolename='INSTRUCTOR' ||\r\n" + 
			"b.rolename='TA') and a.courseid=?;";
	
	public final static String enrollStudentIntoCourse="insert into courserole (roleid,courseid,bannerid) values (5,?,?);";
	
	public final static String getCoursesWhereUserIsStudent="SELECT b.* from courserole as a inner join course as \r\n" + 
			"b on a.courseid=b.courseid where a.roleid=5 and a.bannerid=?;";
	
	public final static String getInstructorForCourse="select bannerid from courserole where roleid=3 and courseid=?;";
	
	public final static String getCoursesWhereUserIsInstructor="SELECT b.* from courserole as a inner join course as \r\n" + 
			"b on a.courseid=b.courseid where a.roleid=3 and a.bannerid=?;";
	
	public final static String getCoursesWhereUserIsTA="SELECT b.* from courserole as a inner join course as \r\n" + 
			"b on a.courseid=b.courseid where a.roleid=4 and a.bannerid=?;";
	
	public final static String passwordTag="@dal";
	
	public final static String isUserExists="select * from user where bannerid= ?;";


}
