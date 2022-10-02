package com.masai.dao;

import java.util.List;

import com.masai.bean.Batch;
import com.masai.bean.BatchDetails;
import com.masai.bean.Course;
import com.masai.bean.CoursePlan;
import com.masai.bean.DayWiseDTO;
import com.masai.bean.Faculty;
import com.masai.bean.FacultyCourseViewDTO;
import com.masai.exception.AdminException;
import com.masai.exception.BatchCException;
import com.masai.exception.CourseException;
import com.masai.exception.CoursePlanException;
import com.masai.exception.FacultyExcepetion;

public interface Admin {
	
	public  String loginAdmin(String username, String password) throws AdminException;
	
	public String addCourse(Course course) throws CourseException;
	public String updateCourse(int courseid) throws CourseException;
	public List<Course> viewCourse() throws CourseException;
	
	public String addBatch(Batch batch) throws BatchCException;
	public String updateBatch(int batchid) throws BatchCException;
	public List<Batch> viewbatch() throws BatchCException;
	
	public String addFaculty(Faculty faculty) throws FacultyExcepetion;
	public String updateFaculty(int facultyid) throws FacultyExcepetion;
	public List<Faculty> viewFaculty() throws FacultyExcepetion;
	
	public String addCoursePlan(CoursePlan cplan) throws CoursePlanException ;
	public String updateCoursePlan(int cpid) throws CoursePlanException;
	public List<CoursePlan> viewCoursePlan() throws CoursePlanException;
	
	public List<DayWiseDTO> dayWiseUpdateBatch(int day) throws CoursePlanException;
	public List<BatchDetails> batchWiseDetails() throws CoursePlanException;
	
	public Faculty loginfaculty(String uname, String pass) throws FacultyExcepetion;
	public List<FacultyCourseViewDTO> facultyCoursePlanView(int Fid) throws FacultyExcepetion;
	public String dayWisePlanner() throws FacultyExcepetion;
	
	public String updateUsernamePasswordFaculty(int fid) throws FacultyExcepetion;
	
}
