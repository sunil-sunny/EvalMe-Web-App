package com.group18.asdc.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.group18.asdc.dao.AdminDao;
import com.group18.asdc.entities.CourseAdmin;


public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminDao admindao;

	@Override
	public String createCourse(CourseAdmin courseadmin) {

		String returnString = "";

		//VALIDATE INPUT

		//1.
		//check if courseid has 4 digits and is a positive integer 
		//if not, set returnString to "invalidid"

		int id = courseadmin.getCourseId();
		String courseid = String.valueOf(courseadmin.getCourseId());
		String coursename = courseadmin.getCourseName().trim();
		String instructid = courseadmin.getInstructorId();


		if(id!=0) {

			if(id<0 || courseid.length()!=4 ) {
				returnString = "invalidid";
				return returnString;
			}

			//check if value of courseId exists in the database already.
			//if it exists, set returnString to "idexists"

			boolean val = admindao.checkCourseId(id);
			if(val==true) {
				returnString = "idexists";
				return returnString;
			}
		}
		else {
			return "idcannotbenull";
		}
		

		//2.
		//check if length of courseName is between 5 to 10 digits
		//if not, set returnString to "shortname"

		if(!coursename.equals(null)) {

			if(coursename.length()<5 || coursename.length()>10) {
				returnString = "shortname";
				return returnString;
			}

			boolean val1 = admindao.checkCourseName(coursename);
			if(val1==true) {
				returnString = "nameexists";
				return returnString;
			}

		}

		//3.
		//check if instructorId starts with a B00
		//if not, set returnString to "invalidinstid"

		if(!instructid.equals(null)) {
			if(instructid.length()!=9 || !instructid.matches("B00(.*)")){
				returnString = "invalidinstid";
				return returnString;
			}
			
			String str = admindao.checkInstructorId(instructid);
				
				return str;
			
		}


		//CREATE COURSE

		//if details are entered in course table, a new course is created.
		//set returnString to "coursecreated"

		
		if(id!=0 && !coursename.equals(null) && instructid.equals(null)) {
		//only 2 arguments, no instructor specified
			
			boolean var = admindao.addCourse(id,coursename);
			
			if(var==true) {
				returnString = "coursecreated";
			}
			else {
				returnString = "coursenotcreated";
			}
			return returnString;
		}
		else if(id!=0 && !coursename.equals(null) && !instructid.equals(null)) {
			//no null arguments
			
			boolean var = admindao.addCourse(courseadmin);
			if(var==true) {
				returnString = "coursecreated";
			}
			else {
				returnString = "coursenotcreated";
			}
			return returnString;
		}
		else if(id!=0 && coursename.equals(null) && !instructid.equals(null)) {
			
			//modify or assign instructor
			boolean var = admindao.addInstructor(id, instructid);
			if(var==true) {
				returnString = "coursecreated";
			}
			else {
				returnString = "coursenotcreated";
			}
			return returnString;
			
		}
		return returnString;
	}

	@Override
	public String deleteCourse(int courseId) {

		if(courseId==0) {
			return "nocourseid";
		}
		else {

			String returnString="";
			String courseid = String.valueOf(courseId);
			boolean val = admindao.checkCourseId(courseId);

			//VALIDATE INPUT

			//check if courseid has 4 digits and is a positive integer 
			//if not, set returnString to "invalidid"

			if(courseId<0 || courseid.length()!=4 ) {
				returnString = "invalidid";
				return returnString;
			}


			//VALIDATE FROM DATABASE 

			//check if value of courseId exists in the database already.
			//if it does not exist, set returnString to "iddoesnotexist"

			//if val is false, course does not exist, to delete.
			if(val==false) {

				returnString = "iddoesnotexist";
				return returnString;
			}

			//DELETE COURSE

			boolean result = admindao.deleteCourse(courseId);

			if(result==true) {
				returnString="coursedeleted";
				return returnString;
			}
			else {
				returnString="coursenotdeleted";
				return returnString;
			}

		}
	}
}
