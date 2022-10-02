package com.masai.bean;

public class BatchDetails {
	
	private int batchid;
	private String batchstartdate;
	private String coursename;
	private int courseid;
	private String facultyname;
	private int facultyid;
	private int fee;
	private int numberofstudents;
	private String courseDescription;
	
	public BatchDetails() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public String toString() {
		return "BatchDetails [batchid=" + batchid + ", batchstartdate=" + batchstartdate + ", coursename=" + coursename
				+ ", courseid=" + courseid + ", facultyname=" + facultyname + ", facultyid=" + facultyid + ", fee="
				+ fee + ", numberofstudents=" + numberofstudents + ", courseDescription=" + courseDescription + "]";
	}
	public BatchDetails(int batchid, String batchstartdate, String coursename, int courseid, String facultyname,
			int facultyid, int fee, int numberofstudents, String courseDescription) {
		super();
		this.batchid = batchid;
		this.batchstartdate = batchstartdate;
		this.coursename = coursename;
		this.courseid = courseid;
		this.facultyname = facultyname;
		this.facultyid = facultyid;
		this.fee = fee;
		this.numberofstudents = numberofstudents;
		this.courseDescription = courseDescription;
	}
	public int getBatchid() {
		return batchid;
	}
	public void setBatchid(int batchid) {
		this.batchid = batchid;
	}
	public String getBatchstartdate() {
		return batchstartdate;
	}
	public void setBatchstartdate(String batchstartdate) {
		this.batchstartdate = batchstartdate;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	public String getFacultyname() {
		return facultyname;
	}
	public void setFacultyname(String facultyname) {
		this.facultyname = facultyname;
	}
	public int getFacultyid() {
		return facultyid;
	}
	public void setFacultyid(int facultyid) {
		this.facultyid = facultyid;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public int getNumberofstudents() {
		return numberofstudents;
	}
	public void setNumberofstudents(int numberofstudents) {
		this.numberofstudents = numberofstudents;
	}
	public String getCourseDescription() {
		return courseDescription;
	}
	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}
	
	
	
	
	
	
}
