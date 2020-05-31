package com.group18.asdc.util;

public class GroupFormationToolUtil {
	
	public final static String getAllCourses="SELECT * FROM CSCI5308_18_DEVINT.course;";
	public final static String getCourseDetails="SELECT a.courseid,a.bannerid,b.rolename FROM CSCI5308_18_DEVINT.courserole as a inner join CSCI5308_18_DEVINT.role as b\r\n" + 
			"on a.roleid=b.roleid where a.courseid=?;";
	public final static String getUserById="SELECT * FROM CSCI5308_18_DEVINT.user where bannerid=?;";

}
