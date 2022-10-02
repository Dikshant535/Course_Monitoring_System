package com.masai.bean;

public class FacultyCourseViewDTO {
	
	private int facultyid;
	private String facultyname;
	private int batchid;
	private String atchstartdate;
	private String topic;
	private String status;
	
	
	public FacultyCourseViewDTO() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "FacultyCourseViewDTO [facultyid=" + facultyid + ", facultyname=" + facultyname + ", batchid=" + batchid
				+ ", atchstartdate=" + atchstartdate + ", topic=" + topic + ", status=" + status + "]";
	}
	public FacultyCourseViewDTO(int facultyid, String facultyname, int batchid, String atchstartdate, String topic,
			String status) {
		super();
		this.facultyid = facultyid;
		this.facultyname = facultyname;
		this.batchid = batchid;
		this.atchstartdate = atchstartdate;
		this.topic = topic;
		this.status = status;
	}
	public int getFacultyid() {
		return facultyid;
	}
	public void setFacultyid(int facultyid) {
		this.facultyid = facultyid;
	}
	public String getFacultyname() {
		return facultyname;
	}
	public void setFacultyname(String facultyname) {
		this.facultyname = facultyname;
	}
	public int getBatchid() {
		return batchid;
	}
	public void setBatchid(int batchid) {
		this.batchid = batchid;
	}
	public String getAtchstartdate() {
		return atchstartdate;
	}
	public void setAtchstartdate(String atchstartdate) {
		this.atchstartdate = atchstartdate;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
