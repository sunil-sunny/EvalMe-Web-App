package com.group18.asdc.entities;

public class Course {

	private int courseId;
	private String CourseName;
	private String instructorName="NA";
	private String taName="NA";

	public Course(int courseId, String courseName,String instructorName,String taName) {
		super();
		this.courseId = courseId;
		this.CourseName = courseName;
		this.instructorName=instructorName;
		this.taName=taName;
	}

	public Course() {
		super();
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return CourseName;
	}

	public void setCourseName(String courseName) {
		CourseName = courseName;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	public String getTaName() {
		return taName;
	}

	public void setTaName(String taName) {
		this.taName = taName;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", CourseName=" + CourseName + ", instructorName=" + instructorName
				+ ", taName=" + taName + "]";
	}

}
