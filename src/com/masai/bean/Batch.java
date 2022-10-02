package com.masai.bean;

public class Batch {
	
	private int batchid;
	private int courseid;
	private int facultyid;
	private int numberofStudents ;
	private String batchstartdate;
	private String duration;
	
	
	public Batch() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Batch(int batchid, int courseid, int facultyid, int numberofStudents, String batchstartdate,
			String duration) {
		super();
		this.batchid = batchid;
		this.courseid = courseid;
		this.facultyid = facultyid;
		this.numberofStudents = numberofStudents;
		this.batchstartdate = batchstartdate;
		this.duration = duration;
	}
	

	@Override
	public String toString() {
		return "Batch [batchid=" + batchid + ", courseid=" + courseid + ", facultyid=" + facultyid
				+ ", numberofStudents=" + numberofStudents + ", batchstartdate=" + batchstartdate + ", duration="
				+ duration + "]";
	}


	public int getBatchid() {
		return batchid;
	}
	public void setBatchid(int batchid) {
		this.batchid = batchid;
	}
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	public int getFacultyid() {
		return facultyid;
	}
	public void setFacultyid(int facultyid) {
		this.facultyid = facultyid;
	}
	public int getNumberofStudents() {
		return numberofStudents;
	}
	public void setNumberofStudents(int numberofStudents) {
		this.numberofStudents = numberofStudents;
	}
	public String getBatchstartdate() {
		return batchstartdate;
	}
	public void setBatchstartdate(String batchstartdate) {
		this.batchstartdate = batchstartdate;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
}
