package com.masai.bean;

public class DayWiseDTO {
	
	private int batchid ;
	private int daynumber;
	private String coursename;
	private  String facultyname;
	private int numberofstudents ;
	private String status;
	
	public DayWiseDTO() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "DayWiseDTO [batchid=" + batchid + ", daynumber=" + daynumber + ", coursename=" + coursename
				+ ", facultyname=" + facultyname + ", numberofstudents=" + numberofstudents + ", status=" + status
				+ "]";
	}
	public DayWiseDTO(int batchid, int daynumber, String coursename, String facultyname, int numberofstudents,
			String status) {
		super();
		this.batchid = batchid;
		this.daynumber = daynumber;
		this.coursename = coursename;
		this.facultyname = facultyname;
		this.numberofstudents = numberofstudents;
		this.status = status;
	}
	public int getBatchid() {
		return batchid;
	}
	public void setBatchid(int batchid) {
		this.batchid = batchid;
	}
	public int getDaynumber() {
		return daynumber;
	}
	public void setDaynumber(int daynumber) {
		this.daynumber = daynumber;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public String getFacultyname() {
		return facultyname;
	}
	public void setFacultyname(String facultyname) {
		this.facultyname = facultyname;
	}
	public int getNumberofstudents() {
		return numberofstudents;
	}
	public void setNumberofstudents(int numberofstudents) {
		this.numberofstudents = numberofstudents;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
}
