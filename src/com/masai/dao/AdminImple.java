package com.masai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.FactoryConfigurationError;

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
import com.masai.utility.DButil;
import com.mysql.cj.xdevapi.DbDoc;

public class AdminImple implements Admin{

	@Override
	public String loginAdmin(String username, String password) throws AdminException {
		String msg=null;
		boolean flag = false ;
		
		try (Connection conn = DButil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from admin");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				String usern = rs.getString("username");
				String userp = rs.getString("password");
				
				if(username.equals(usern)&&password.equals(userp)) {
					
					flag=true;
				}
				if(flag)
					break;
				
				
			}
			
			if(flag)
				msg="Admin Sucessfully Login.....!";
			else
				throw new AdminException("Incorrect Credidential");
			
			
			
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
			
		}
		
		
		
		return msg;
	}

	@Override
	public String addCourse(Course course) throws CourseException {
		
//		boolean flag = false;
		String msg = "Not added";
		
		try (Connection conn = DButil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("insert into course(coursename,fee,courseDescription) values(?,?,?)");
			ps.setString(1, course.getCoursename());
			ps.setInt(2, course.getFee());
			ps.setString(3,course.getCourseDescription());
			
			int x = ps.executeUpdate();
			
			if(x>0)
//				flag=true;
				msg="Course added sucessfully....!";
			else
				throw new CourseException("Not Added...!");
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return msg;
	}

	@Override
	public String updateCourse(int cid) throws CourseException {
		String msg = "Provide Valid details";
		Scanner sc= new Scanner(System.in);
		try (Connection conn = DButil.provideConnection()){
			
		PreparedStatement ps	= conn.prepareStatement("select courseid from course where courseid=?");
			
			ps.setInt(1, cid);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				
				boolean check = true;
				while(check) {
					System.out.println();
					System.out.println("Please select your choice...."+"\n"+"1. Update fee"+"\n"+"2. Update Description"+"\n"+"3.Update Coursename");
					
					int ch = sc.nextInt();
					
					switch (ch) {
//					check = false;
					case 1: 
						
						System.out.println("Enter fee");
						int fee = sc.nextInt();
//						sc.nextLine();
						
						PreparedStatement sp = conn.prepareStatement("update course set fee=? where courseid=?");
						sp.setInt(1, fee);
						sp.setInt(2, cid);
						
					 	int rs1 = sp.executeUpdate();
						
					 	if(rs1>0) {
//					 		System.out.println("Fee updated Sucessfully......");
					 		msg = "Fee updated Sucessfully......";
					 	}
					 	else
					 		System.out.println("Fee not updated......");
					 	
					 	check = false;
					break;
					case 2: {
						
						System.out.println("Enter Descriprtion");
						sc.nextLine();
						String desc = sc.nextLine();
//						sc.nextLine();
						
						PreparedStatement sp1 = conn.prepareStatement("update course set courseDescription=? where courseid=?");
						sp1.setString(1, desc);
						sp1.setInt(2, cid);
						
					 	int rs2 = sp1.executeUpdate();
						
					 	if(rs2>0) {
//					 		System.out.println("Description updated Sucessfully......");
					 		msg = "Description updated Sucessfully......";
					 	}
					 		
					 	else
					 		System.out.println("Description not updated......");
					 	check = false;
					break;
						
						
					}
					case 3: {
						System.out.println("Enter Course name");
						String cname = sc.next();
						
						PreparedStatement sp2 = conn.prepareStatement("update course set coursename=? where courseid=?");
						sp2.setString(1, cname);
						sp2.setInt(2, cid);
						
					 	int rs3 = sp2.executeUpdate();
						
					 	if(rs3>0) {
//					 		System.out.println("Course name updated Sucessfully......");
					 		msg = "Course name updated Sucessfully......";
					 	}
					 		
					 	else
					 		System.out.println("Course name not updated......");
					 	check = false;
					break;
						
					}
					default:
						System.out.println("Invalid value,please enter under choice value....");
						check = true;
					}
					
				}
				
				
				
				
			}else
				throw new CourseException("Invalid Courseid....");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			
		}
		
		
		return msg;
	}

	@Override
	public List<Course> viewCourse() throws CourseException {
		
		
		List<Course> listC = new ArrayList<>();
		
		try (Connection conn = DButil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("Select * from course");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Course course = new Course();
				
				course.setCourseid(rs.getInt("courseid"));
				course.setCoursename(rs.getString("coursename"));
				course.setCourseDescription(rs.getString("courseDescription"));
				
				listC.add(course);
				
				
				
			}
			
			
			
		} catch (SQLException e) {
			throw new CourseException(e.getMessage());
		}
		
		if(listC.size()==0)
			throw new CourseException("No course founnd...");
		
		return listC;
		
	}

	
	
	@Override
	public String addBatch(Batch batch) throws BatchCException {
		String msg = "Not added";
		
		try (Connection conn = DButil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("insert into Batch(courseid,facultyid,numberofStudents,batchstartdate,duration) values(?,?,?,?,?)");
			ps.setInt(1, batch.getCourseid());
			ps.setInt(2, batch.getFacultyid());
			ps.setInt(3, batch.getNumberofStudents());
			ps.setString(4, batch.getBatchstartdate());
			ps.setString(5, batch.getDuration());
			
			int b = ps.executeUpdate();
			if(b>0)
				msg = "Batch added Sucessfully";
			else
				throw new BatchCException("Batch not Added");
			
			
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		
		
		return msg;
	}

	@Override
	public String updateBatch(int baid) throws BatchCException {
		// TODO Auto-generated method stub
		String msg = null ;
		Scanner sc= new Scanner(System.in);
		boolean flag = true;
		try (Connection conn = DButil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select batchid from batch where batchid =? ");
			ps.setInt(1, baid);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				while(flag) {
					
					System.out.println("Please select your choice...."+"\n"+"1. Update courseid"+"\n"+"2. Update facultyid"+"\n"+"3. Update number of students"+"\n"+"4. Update batch start date"+"\n"+"5. Update duration");
					int ch= sc.nextInt();
					
					switch (ch) {
					case 1: 
						System.out.println("Enter Course ID");
						int cid=sc.nextInt();
						PreparedStatement bs1= conn.prepareStatement("update batch set courseid=? where batchid=?");
						bs1.setInt(1, cid);
						bs1.setInt(2, baid);
						int x=bs1.executeUpdate();
						if(x>0)
						{
							msg="Batchid Successfully Updated";
						}else{
							throw new BatchCException("Not Updated");
						}
						flag = false;
					    break;
					case 2: 
						System.out.println("Enter Faculty Id");
						int fid=sc.nextInt();
						PreparedStatement bs2= conn.prepareStatement("update batch set facultyid=? where batchid=?");
						bs2.setInt(1, fid);
						bs2.setInt(2, baid);
						int x1=bs2.executeUpdate();
						if(x1>0)
						{
							msg="Faculty id Successfully Updated";
						}else{
							throw new BatchCException("Not Updated");
						}
						flag = false;
				        break;
					case 3:
						System.out.println("Enter Number of Students");
						int ns=sc.nextInt();
						PreparedStatement bs3= conn.prepareStatement("update batch set numberofStudents=? where batchid=?");
						bs3.setInt(1, ns);
						bs3.setInt(2, baid);
						int x2=bs3.executeUpdate();
						if(x2>0)
						{
							msg="No of Students Successfully Updated";
						}else{
							throw new BatchCException("Not Updated");
						}
						flag = false;
				       break;
					case 4: 
						System.out.println("Enter Date yyyy-mm-dd");
						String date=sc.next();
						PreparedStatement bs4=conn.prepareStatement("update batch set batchstartdate=? where batchid=?");
						bs4.setString(1, date);
						bs4.setInt(2, baid);
						int x3=bs4.executeUpdate();
						if(x3>0)
						{
							msg="Batch Start Date Updated Succesfully";
						}else {
							throw new BatchCException("Not Updated");
						}
						flag = false;
				        break;
					case 5: 
						System.out.println("Enter Course Duration");
						String duration=sc.next();
						PreparedStatement bs5=conn.prepareStatement("update batch set duration=? where batchid=?");
						bs5.setString(1, duration);
						bs5.setInt(2, baid);
						int x4=bs5.executeUpdate();
						if(x4>0)
						{
							msg="Duration Updated Succesfully";
						}else {
							throw new BatchCException("Not Updated");
						}
						flag = false;
				        break;
					default:
						System.out.println("Enter Valid value uder the choise");
						flag = true;
					}
					
					
				}
				
			}else
				throw new BatchCException("Kindly enter valis batchid");
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new BatchCException("Enter valid batchid");
			
			
		}
		
		
		return msg;
	}

	@Override
	public List<Batch> viewbatch() throws BatchCException {
		
		List<Batch> batchs = new ArrayList<>();
		
		try (Connection conn = DButil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from batch");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Batch ab = new Batch(rs.getInt("batchid"),rs.getInt("courseid"), rs.getInt("facultyid"), rs.getInt("numberofStudents"), rs.getString("batchstartdate"), rs.getString("duration"));
				
				batchs.add(ab);
				
				
			}
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			
		}
				
		if(batchs.size()==0) {
			
			throw new BatchCException("No records find");
		}
		
		return batchs;
	}

	@Override
	public String addFaculty(Faculty faculty) throws FacultyExcepetion {
		String msg="Not added";
		
		try(Connection conn=DButil.provideConnection()) {
			PreparedStatement fa=conn.prepareStatement("insert into faculty(facultyname,facultyaddress,mobile,email,username,password) values(?,?,?,?,?,?)");
			
			
			fa.setString(1,faculty.getFacultyname());
			fa.setString(2, faculty.getFacultyaddress());
			fa.setString(3, faculty.getMobile());
			fa.setString(4, faculty.getEmail());
			fa.setString(5, faculty.getUsername());
			fa.setString(6, faculty.getPassword());
			
			int fan = fa.executeUpdate();
			
			if(fan>0)
				msg="Faculty added successfully......";
			else
				throw new FacultyExcepetion("Not added");
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			
			throw new FacultyExcepetion(e.getMessage());
			
		}
		
		return msg;
	}

	
	
	@Override
	public String updateFaculty(int facultyid) throws FacultyExcepetion {
		String msg = null ;
		Scanner sc= new Scanner(System.in);
		boolean flag = true;
		try(Connection conn=DButil.provideConnection()) {
			
			PreparedStatement fa=conn.prepareStatement("select facultyid from faculty where facultyid=?");
			fa.setInt(1, facultyid);
			ResultSet rs = fa.executeQuery();
			if(rs.next())
			{
				while(flag)
				{
					System.out.println("Please select your choice...."+"\n"+"1. Update faculty Name"+"\n"+"2. Update faculty Address"+"\n"+"3. Update Mobile Number"+"\n"+"4. Update Email"+"\n"+"5. Update Username"+"\n"+"6.update Password");
					int ch= sc.nextInt();
					
					switch (ch) {
					case 1: 
						System.out.println("Enter Faculty Name");
					String fname=sc.next();
						PreparedStatement ps1= conn.prepareStatement("update faculty set facultyname=? where facultyid=?");
						ps1.setString(1,fname);
						ps1.setInt(2, facultyid);
						int x=ps1.executeUpdate();
						if(x>0)
						{
							msg="Faculty name Successfully Updated";
						}else{
							throw new FacultyExcepetion("Not Updated");
						}
						flag = false;
					    break;
					case 2: 
						System.out.println("Enter faculty Address");
					    String fadd=sc.next();
						PreparedStatement ps2= conn.prepareStatement("update faculty set facultyaddress=? where facultyid=?");
						ps2.setString(1,fadd);
						ps2.setInt(2, facultyid);
						int x1=ps2.executeUpdate();
						if(x1>0)
						{
							msg="Faculty Address Successfully Updated";
						}else{
							throw new FacultyExcepetion("Not Updated");
						}
						flag = false;
					    break;
					case 3: 
						System.out.println("Enter faculty Mobile number");
					    String mobile=sc.next();
						PreparedStatement ps3= conn.prepareStatement("update faculty set mobile=? where facultyid=?");
						ps3.setString(1,mobile);
						ps3.setInt(2, facultyid);
						int x2=ps3.executeUpdate();
						if(x2>0)
						{
							msg="Faculty Mobile Successfully Updated";
						}else{
							throw new FacultyExcepetion("Not Updated");
						}
						flag = false;
					    break;
					case 4: 
						System.out.println("Enter faculty Email");
					    String email=sc.next();
						PreparedStatement ps4= conn.prepareStatement("update faculty set email=? where facultyid=?");
						ps4.setString(1,email);
						ps4.setInt(2, facultyid);
						int x3=ps4.executeUpdate();
						if(x3>0)
						{
							msg="Faculty Email Successfully Updated";
						}else{
							throw new FacultyExcepetion("Not Updated");
						}
						flag = false;
					    break;
					case 5: 
						System.out.println("Enter faculty Username");
					    String username=sc.next();
						PreparedStatement ps5= conn.prepareStatement("update faculty set username=? where facultyid=?");
						ps5.setString(1,username);
						ps5.setInt(2, facultyid);
						int x4=ps5.executeUpdate();
						if(x4>0)
						{
							msg="Faculty username Successfully Updated";
						}else{
							throw new FacultyExcepetion("Not Updated");
						}
						flag = false;
					    break;
					case 6: 
						System.out.println("Enter faculty password");
					    String password=sc.next();
						PreparedStatement ps6= conn.prepareStatement("update faculty set password=? where facultyid=?");
						ps6.setString(1,password);
						ps6.setInt(2, facultyid);
						int x5=ps6.executeUpdate();
						if(x5>0)
						{
							msg="Faculty Password Successfully Updated";
						}else{
							throw new FacultyExcepetion("Not Updated");
						}
						flag = false;
					    break;
					default:
						System.out.println("Enter Valid value uder the choice");
						flag = true;
					}
					
					
				}
			}
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			
			throw new FacultyExcepetion(e.getMessage());
		}
		
		
		
		
		
		
		
		return msg;
	}

	@Override
	public List<Faculty> viewFaculty() throws FacultyExcepetion {
		List<Faculty> faculties = new ArrayList<>();
		
		try (Connection conn = DButil.provideConnection()){
			
			PreparedStatement fac = conn.prepareStatement("select * from faculty");
			ResultSet rs =  fac.executeQuery();
			
			while(rs.next()) {
				
				Faculty faculty = new Faculty(rs.getInt("facultyid"), rs.getString("facultyname"), rs.getString("facultyaddress"),  rs.getString("mobile"),  rs.getString("email"),  rs.getString("username"),  rs.getString("password"));
				
				faculties.add(faculty);
				
				
			}
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new FactoryConfigurationError(e.getMessage());
		}
		
		if(faculties.size()==0)
			throw new FacultyExcepetion("No records found");
		
		
		return faculties;
	}

	
	
	@Override
	public String addCoursePlan(CoursePlan cplan) throws CoursePlanException {
		String msg = "Course plan not added";
		
		try (Connection conn = DButil.provideConnection()){
			
			PreparedStatement cp = conn.prepareStatement("insert into courseplan(batchid,daynumber,topic,status) values(?,?,?,?)");
			
			cp.setInt(1,cplan.getBatchid());
			cp.setInt(2,cplan.getDaynumber());
			cp.setString(3,cplan.getTopic());
			cp.setString(4,cplan.getStatus());
			
			int x = cp.executeUpdate();
			
			if(x>0)
				msg ="Course plan sucessfully added";
			else
				throw new CoursePlanException("Course plan not added");
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new CoursePlanException(e.getMessage());
		}
		
		
		
		
		return msg;
	}

	@Override
	public String updateCoursePlan(int cpid) throws CoursePlanException {
		String msg = "Not update course plan";
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		try (Connection conn = DButil.provideConnection()){
			
			PreparedStatement ucp = conn.prepareStatement("select planid from courseplan where planid=?");
			ucp.setInt(1, cpid);
			
			ResultSet rs =  ucp.executeQuery();
			
			while(flag) {
				System.out.println("ENter choice for update"+"\n"+"1. Update batch id"+"\n"+"2. Update day number"+"\n"+"3. Update topic"+"\n"+"4. Update Staus");
				int ch = sc.nextInt();
				if(rs.next()) {
					
					
					
					switch (ch) {
					case 1: 
						System.out.println("Enter batch id");
						int bi = sc.nextInt();
						
						PreparedStatement ucp1 = conn.prepareStatement("update courseplan set batchid =? where planid=?");
						ucp1.setInt(1, bi);
						ucp1.setInt(2,cpid);
						
						int x = ucp1.executeUpdate();
						
						if(x>0)
							msg = "batchid updated";
						else
							throw new CoursePlanException("not updated");
						
						flag = false ;
						break;
						
					case 2: 
						
						System.out.println("Enter day number ");
						int nd = sc.nextInt();
						
						PreparedStatement ucp2 = conn.prepareStatement("update courseplan set daynumber =? where planid=?");
						ucp2.setInt(1, nd);
						ucp2.setInt(2,cpid);
						
						int x1 = ucp2.executeUpdate();
						
						if(x1>0)
							msg = "Day number updated";
						else
							throw new CoursePlanException("not updated");
						
						flag = false ;
						break;
						
					
					case 3: 
						sc.nextLine();
						System.out.println("Enter Topic ");
						String tp = sc.nextLine();
						
						PreparedStatement ucp3 = conn.prepareStatement("update courseplan set topic =? where planid=?");
						ucp3.setString(1, tp);
						ucp3.setInt(2,cpid);
						
						int x2 = ucp3.executeUpdate();
						
						if(x2>0)
							msg = "Topic updated";
						else
							throw new CoursePlanException("not updated");
						
						flag = false ;
						break;
						
						
					case 4: 
						sc.nextLine();
						System.out.println("Enter Status ");
						String st = sc.nextLine();
						
						PreparedStatement ucp4 = conn.prepareStatement("update courseplan set status =? where planid=?");
						ucp4.setString(1, st);
						ucp4.setInt(2,cpid);
						
						int x3 = ucp4.executeUpdate();
						
						if(x3>0)
							msg = "Status updated";
						else
							throw new CoursePlanException("not updated");
						
						flag = false ;
						break;
						
					default:
						System.out.println("Enter valid value under the choice given...");
						
						flag= true;
					}
					
					
				}else
					throw new CoursePlanException("Enter Valid plan id....");
				
				
			}
			
			
			
		} catch (Exception e) {
			throw new CoursePlanException(e.getMessage());
		}
		
		
		return msg;
	}

	@Override
	public List<CoursePlan> viewCoursePlan() throws CoursePlanException {
		List<CoursePlan> courseplans = new ArrayList<>();
		
		try (Connection conn = DButil.provideConnection()){
			
			PreparedStatement cp = conn.prepareStatement("select * from courseplan");
			
			ResultSet rs = cp.executeQuery();
			
			while(rs.next()) {
				
				CoursePlan cplann = new CoursePlan(rs.getInt("planid"), rs.getInt("batchid"), rs.getInt("daynumber"), rs.getString("topic"), rs.getString("status"));
				
				courseplans.add(cplann);
				
			}
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new CoursePlanException(e.getMessage());
		}
		
		if(courseplans.size()==0)
			throw new CoursePlanException("N records found");
		
		return courseplans;
	}

	@Override
	public List<DayWiseDTO> dayWiseUpdateBatch(int day) throws CoursePlanException {
		List<DayWiseDTO> daysBatch = new ArrayList<>();
		
		try (Connection conn = DButil.provideConnection()){
			
			PreparedStatement dw = conn.prepareStatement("select b.batchid,cp.daynumber,c.coursename,f.facultyname,b.numberofstudents ,cp.status from faculty f inner join batch b  on f.facultyid = b.facultyid inner join course c on  c.courseid = b.courseid inner join courseplan cp on  b.batchid = cp.batchid where cp.daynumber = ?");
			
			dw.setInt(1, day);
			
			ResultSet rs = dw.executeQuery();
			
			while(rs.next()) {
				
				DayWiseDTO dwd = new DayWiseDTO(rs.getInt("batchid"), rs.getInt("daynumber"), rs.getString("coursename"), rs.getString("facultyname"), rs.getInt("numberofstudents"), rs.getString("status"));
				
				daysBatch.add(dwd);
				
			}
			
		} catch (SQLException e) {
			throw new CoursePlanException(e.getMessage());
		}
		
		if(daysBatch.size()==0)
			throw new CoursePlanException("No records found");
		
		return daysBatch;
	}

	
	
	@Override
	public List<BatchDetails> batchWiseDetails() throws CoursePlanException {
		
		List<BatchDetails> batchsD = new ArrayList<>();
		
		try (Connection conn = DButil.provideConnection()){
			
			PreparedStatement bwd = conn.prepareStatement("select b.batchid,b.batchstartdate,c.coursename,c.courseid,f.facultyname,f.facultyid,c.fee,b.numberofstudents,c.courseDescription from faculty f inner join batch b  on f.facultyid = b.facultyid inner join course c on  c.courseid = b.courseid inner join courseplan cp on  b.batchid = cp.batchid group by batchid order by batchid");
			
			ResultSet rs = bwd.executeQuery();
			
			while(rs.next()) {
				
				BatchDetails bd = new BatchDetails(rs.getInt("batchid"), rs.getString("batchstartdate"), rs.getString("coursename"), rs.getInt("courseid"), rs.getString("facultyname"), rs.getInt("facultyid"),rs.getInt("fee"),rs.getInt("numberofstudents"), rs.getString("courseDescription"));
				
				batchsD.add(bd);
				
			}
			
			
		} catch (SQLException e) {
			throw new CoursePlanException(e.getMessage());
		}
		
		if(batchsD.size()==0)
			throw new CoursePlanException("No Records Found");
		
		return batchsD;
	}

	
	
	@Override
	public Faculty loginfaculty(String uname, String pass) throws FacultyExcepetion {
		
    Faculty faculty = null ;
		
		try(Connection conn = DButil.provideConnection()) {
			
			
			PreparedStatement ps= conn.prepareStatement("select * from faculty where username = ? AND password = ?");			
			
			ps.setString(1, uname);
			ps.setString(2, pass);
			
			ResultSet rs= ps.executeQuery();
			
			
				if(rs.next()) {
				
				String fname= rs.getString("facultyname");
				String faddress=rs.getString("facultyaddress");
				String mobile=rs.getString("mobile");
				String email=rs.getString("email");
				String username=rs.getString("username");
				String password=rs.getString("password");
				
				faculty = new Faculty();
				faculty.setFacultyid(rs.getInt("facultyid"));
				faculty.setFacultyname(fname);
				faculty.setFacultyaddress(faddress);
				faculty.setMobile(mobile);
				faculty.setEmail(email);
				faculty.setUsername(username);
				faculty.setPassword(password);
				
				
				
			}else
				throw new FacultyExcepetion("Invalid Username or password.. / you need to login again");
			
			
		} catch (SQLException e) {
			throw new FacultyExcepetion(e.getMessage());
		}
		
		
		return faculty;
	}

	
	@Override
	public List<FacultyCourseViewDTO> facultyCoursePlanView(int fid) throws FacultyExcepetion {
		
		List<FacultyCourseViewDTO> fac = new ArrayList<>() ;
		
		try(Connection conn = DButil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("select f.facultyid ,f.facultyname,b.batchid,b.batchstartdate,cp.topic,cp.status from faculty f inner join batch b on f.facultyid=b.facultyid inner join courseplan cp on b.batchid=cp.batchid where f.facultyid = ? group by b.batchid");
			
			ps.setInt(1, fid);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				FacultyCourseViewDTO fxd = new FacultyCourseViewDTO(rs.getInt("facultyid"), rs.getString("facultyname"), rs.getInt("batchid"), rs.getString("batchstartdate"), rs.getString("topic"), rs.getString("status"));
				
				fac.add(fxd);
				
				
				
			}
			
			
			
			
		} catch (SQLException e) {
			throw new FacultyExcepetion(e.getMessage());
		}
		
		if(fac.size()==0)
			throw new FacultyExcepetion("No record found");
		
		
		return fac;
	}

	
	@Override
	public String dayWisePlanner() throws FacultyExcepetion {
		
		String msg = "not added";
		Scanner sc = new Scanner(System.in);
		try (Connection conn = DButil.provideConnection()){
			
			System.out.println("Enter the details of day wise planner details");
			System.out.println();
			System.out.println("Enter Batch Id");
			int bid = sc.nextInt();
			System.out.println("Enter Day Number");
			int dayn = sc.nextInt();
			System.out.println("Enter Topic");
			sc.nextLine();
			String top = sc.nextLine();
			System.out.println("Enter Status");
			String stst = sc.nextLine();
			
			PreparedStatement ps= conn.prepareStatement("insert into courseplan(batchid,daynumber,topic,status) values(?,?,?,?)");
			
			ps.setInt(1, bid);
			ps.setInt(2,dayn );
			ps.setString(3,top);
			ps.setString(4,stst);
			
			int x = ps.executeUpdate();
			
			if(x>0)
				msg = "Day wise Planner added..!";
			else
				throw new FacultyExcepetion("Not added");
			
		} catch (SQLException e) {
			throw new FacultyExcepetion(e.getMessage());
		}
		
		
		
		return msg;
	}


	
	@Override
	public String updateUsernamePasswordFaculty(int fid) throws FacultyExcepetion {
		String msg = "Not updated";
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		try (Connection conn = DButil.provideConnection()){
			
			while(flag) {
				
				System.out.println("Enter Choice"+"\n"+"1. Update Username"+"\n"+"2. Update Password");
				int ch = sc.nextInt();
				
				switch (ch) {
				case 1: {
					
					System.out.println("Enter username for update");
					String unam = sc.next();
					
					PreparedStatement ps = conn.prepareStatement("update faculty set username=? where facultyid=?");
					ps.setString(1, unam);
					ps.setInt(2, fid);
					
					int x = ps.executeUpdate();
					if(x>0)
						msg="Username updated Successfully";
					else
						throw new FacultyExcepetion("Not updated");
					
					flag = false;
					break;
				}
				case 2: {
					
					System.out.println("Enter password for update");
					String pass = sc.next();
					
					PreparedStatement ps = conn.prepareStatement("update faculty set password=? where facultyid=?");
					ps.setString(1, pass);
					ps.setInt(2, fid);
					
					int x = ps.executeUpdate();
					if(x>0)
						msg="Password updated Successfully";
					else
						throw new FacultyExcepetion("Not updated");
					
					flag = false;
					break;
				}
				default:
					System.out.println("Enter valid details under choice");
					flag = true;
				}
				
			}
			
			
		} catch (SQLException e) {
			throw new FacultyExcepetion(e.getMessage());
		}
		
		
		
		return msg;
	}

	
	
	

	
	

	

}
