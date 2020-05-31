package com.group18.asdc.util;

public class GroupFormationToolUtil {
	
	public static String getAllCourses="SELECT * FROM CSCI5308_18_DEVINT.course;";
	public static String getCourseDetailsbyId="SELECT a.courseid,a.coursename,CONCAT(d.firstname, \" \", d.lastname) AS instructorname,c.rolename FROM CSCI5308_18_DEVINT.course as a \r\n" + 
			"left join CSCI5308_18_DEVINT.courserole as b on a.courseid=b.courseid\r\n" + 
			"inner join CSCI5308_18_DEVINT.role as c on b.roleid=c.roleid\r\n" + 
			"inner join CSCI5308_18_DEVINT.user as d on b.bannerid=d.bannerid\r\n" + 
			"where a.courseid=?;\r\n" + 
			"";

}
