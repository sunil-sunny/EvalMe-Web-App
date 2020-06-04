package com.group18.asdc.service;


import com.group18.asdc.entities.CourseAdmin;

public class AdminServiceImpl implements AdminService{

	@Override
	public String createCourse(CourseAdmin courseadmin) {

		String returnString = "";

		//1.
		//check if courseid has 4 digits and is a positive integer 
		//if not, set returnString to "invalidid"

		String courseid = String.valueOf(courseadmin.getCourseId());
		String coursename = courseadmin.getCourseName().strip();
		String instructid = courseadmin.getInstructorId().strip();

		if(courseadmin.getCourseId()<0 || courseid.strip().length()!=4 ) {
			returnString = "invalidid";
			return returnString;
		}

		//2.
		//check if length of courseName is between 5 to 10 digits
		//if not, set returnString to "shortname"



		else if(coursename.length()<5 || coursename.length()>10) {
			returnString = "shortname";
			return returnString;
		}

		//3.
		//check if instructorId starts with a B00
		//if not, set returnString to "invalidinstid"

		else if(instructid.length()!=9 || !instructid.matches("B00(.*)")){
			returnString = "invalidinstid";
			return returnString;
		}
		
		else {
			returnString = "coursecreated";
			return returnString;
		}

	}

	@Override
	public String deleteCourse(int courseId) {

		String returnString="";

		String courseid = String.valueOf(courseId);

		//1.
		//check if courseid has 4 digits and is a positive integer 
		//if not, set returnString to "invalidid"
		if(courseId<0 || courseid.strip().length()!=4 ) {
			returnString = "invalidid";
			return returnString;
		}

		else {
			return returnString;
		}	
	}
}
