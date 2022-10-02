package com.masai.usecase;

import java.util.List;
import java.util.Scanner;

import com.masai.bean.Batch;
import com.masai.bean.BatchDetails;
import com.masai.bean.Course;
import com.masai.bean.CoursePlan;
import com.masai.bean.DayWiseDTO;
import com.masai.bean.Faculty;
import com.masai.bean.FacultyCourseViewDTO;
import com.masai.dao.Admin;
import com.masai.dao.AdminImple;
import com.masai.exception.AdminException;
import com.masai.exception.BatchCException;
import com.masai.exception.CourseException;
import com.masai.exception.CoursePlanException;
import com.masai.exception.FacultyExcepetion;



public class Main {

	public static void main(String[] args) {
		
		int facultyID;
		boolean exit = false;
		while(!exit) {
			
			System.out.println("==============================================");
			System.out.println("Welcome Course Montoring System");
			System.out.println();
			System.out.println("1.Login as Admin");
			System.out.println("2.Login as Faculty");
			System.out.println("==============================================");
			
			Admin ad = new AdminImple();
			
			Scanner sc= new Scanner(System.in);
			int choice= sc.nextInt();
			boolean flag = true;
			if(choice==1)
			{
				System.out.println("Admin login");
				
				System.out.println("Enter Username:");
				String uname = sc.next();
				
				System.out.println("Enter Password:");
				String pass = sc.next();
				boolean quit = false;
				while(!quit) {
					
				
					try {
						
				 		
				 		while(flag) {
				 			String admin= ad.loginAdmin(uname, pass);
		//					System.out.println("Login Sucessfull...");
							System.out.println("==============================================");
					 		System.out.println("Welcome To Admin :");
					 		System.out.println();
					 		System.out.println("Enter your choice");
					 		System.out.println("1. Create course");
					 		System.out.println("2. Update course");
					 		System.out.println("3. View Course");
					 		System.out.println("4. Create batch");
					 		System.out.println("5. Update Batch");
					 		System.out.println("6. View Batch with passing course Name");
					 		System.out.println("7. Create Faculty");
					 		System.out.println("8. Update Faculty");
					 		System.out.println("9. View Faculty");
					 		System.out.println("10. Create course Plan");
					 		System.out.println("11. Update course Plan");
					 		System.out.println("12. View course Plan");
					 		System.out.println("13. View the Day wise Update of every Batch");
					 		System.out.println("14. GenrateReport of every Batch");
					 		System.out.println("15. Logout");
					 		
					 		System.out.println("==============================================");
					 		
					 		int ch = sc.nextInt();
				 			switch (ch) {
				 			
				 			
					 		  case 1:
					 		    
					 			  System.out.println("Enter the details of course");
					 			  System.out.println();
					 			  System.out.println("Enter the course name");
					 			  sc.nextLine();
					 			  String cname = sc.nextLine();
					 			  System.out.println("Enter fee");
					 			  int fee = sc.nextInt();
					 			  System.out.println("Enter course description");
					 			  sc.nextLine();
					 			  String desc = sc.nextLine();
					 			  
					 			  Course course = new Course();
					 			  course.setCoursename(cname);
					 			  course.setFee(fee);
					 			  course.setCourseDescription(desc);
					 			  
					 			  
					 			  try {
									String ans = ad.addCourse(course);
									System.out.println(ans);
									
								} catch (CourseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
					 			flag = true;  
					 		    break;
					 		  case 2:
					 			
					 			System.out.println("Enter Course id.....");
					 			int id = sc.nextInt();
					 			
					 			try {
					 				
					 				String ans = ad.updateCourse(id);
					 				System.out.println(ans);
					 				
					 			} catch (CourseException e) {
					 				
					 				System.out.println(e.getMessage());
					 				
					 			} 
					 			  
					 			  
					 		    flag=true;
					 		    break;
					 		  case 3:
					 		    
					 			 try {
					 				List<Course> listC = ad.viewCourse();
					 				System.out.println("Details of course");
					 				listC.forEach(s -> {
					 					System.out.println();
					 					
					 					System.out.println("Course Id "+s.getCourseid());
					 					System.out.println("Course name "+s.getCoursename());
					 					System.out.println("Course Descriptio "+s.getCourseDescription());
					 					System.out.println();
					 					System.out.println("====================================================================");
					 					
					 					
					 				});
					 				
					 			} catch (CourseException e) {
					 				
					 				System.out.println(e.getMessage());
					 				
					 			}  
					 			  
					 		    flag=true;
					 		    break;
					 		  case 4:
					 		    
					 			  System.out.println("Enter batch details");
					 			  System.out.println("Enter course id");
					 			  int cid = sc.nextInt();
					 			 System.out.println("Enter faculty id");
					 			 int fid = sc.nextInt();
					 			 System.out.println("Enter Number of Students");
					 			 int nos = sc.nextInt();
					 			 System.out.println("Enter batch start date");
					 			 String bsd = sc.next();
					 			 System.out.println("Enter Duration");
					 			 sc.nextLine();
					 			 String dur = sc.nextLine();
					 			Batch ba = new Batch();  
					 			ba.setCourseid(cid);
					 			ba.setFacultyid(fid);
					 			ba.setNumberofStudents(nos);
					 			ba.setBatchstartdate(bsd);
					 			ba.setDuration(dur);
					 			
					 			  
					 			 try {
					 				String ab = ad.addBatch(ba);
					 				System.out.println(ab);
					 			} catch (BatchCException e) {
					 				// TODO Auto-generated catch block
					 				e.printStackTrace();
					 				System.out.println(e.getMessage());
					 			}
					 			  
					 			  
					 		    flag=true;
					 		    break;
					 		  case 5:
					 		    System.out.println("Enter batch id");
					 			 int bid = sc.nextInt();
					 			
					 			try {
					 				
					 				String ans = ad.updateBatch(bid);
					 				System.out.println(ans);
					 				
					 			} catch (BatchCException e) {
		
					 				e.printStackTrace();
					 				
					 			}
					 			  
					 			  
					 		   flag=true;
					 		    break;
					 		  case 6:
					 			  
					 			  System.out.println("Detais of batchs");
					 			 try {
					 				List<Batch> batchs = ad.viewbatch();
					 				
					 				batchs.forEach(s -> System.out.println(s));
					 				
					 				
					 			} catch (Exception e) {
					 				// TODO: handle exception
					 			}
					 			  
					 			  flag=true;
					 		    break;
					 		  case 7:
					 		    
					 			  System.out.println("Enter details of faculty");
					 			  System.out.println();
					 			  System.out.println("Enter faculty name");
					 			  String fname = sc.next();
					 			  System.out.println("Enter faculty address");
					 			  sc.nextLine();
					 			  String fadd = sc.nextLine();
					 			  System.out.println("Enter faculty mobile number");
					 			  String fmob = sc.next();
					 			  System.out.println("Enter faculty email");
					 			  String fmail = sc.next();
					 			  System.out.println("Enter Faculty username");
					 			  String fuser = sc.next();
					 			  System.out.println("Enter faculty password");
					 			  String fpass = sc.next();
					 			  Faculty fac= new Faculty();
					 			  fac.setFacultyname(fname);
					 			 fac.setFacultyaddress(fadd);
					 			
					 			  fac.setMobile(fmob);
					 			  fac.setEmail(fmail);
					 			  fac.setUsername(fuser);
					 			  fac.setPassword(fpass);
					 			  
					 			 try {
					 				String ans = ad.addFaculty(fac);
					 				System.out.println(ans);
					 			} catch (FacultyExcepetion e) {
					 				
					 				e.printStackTrace();
					 			}
					 			  
					 		   flag=true;
					 		    break;
					 		  case 8:
						 		 System.out.println("Enter faculty id");
					 			 int ffid = sc.nextInt();
					 			
					 			try {
					 				String ans = ad.updateFaculty(ffid);
					 				System.out.println(ans);
					 			} catch (FacultyExcepetion e) {
					 				// TODO Auto-generated catch block
					 				e.printStackTrace();
					 			}
					 			  
						 		   flag=true;
						 		    break;
					 		  case 9:
					 			  System.out.println("Faculty details");
					 			 try {
					 				List<Faculty> faculties = ad.viewFaculty();
					 				faculties.forEach(s -> System.out.println(s));
					 				
					 				
					 			} catch (FacultyExcepetion e) {
					 				
					 				e.printStackTrace();
					 			}
					 			  
					 			 flag=true;
					 		    	break;
					 		  case 10:
					 			  System.out.println("Enter course plan details");
					 			  System.out.println("Enter batch id");
					 			  int cpid = sc.nextInt();
					 			 System.out.println("Enter daynumber");
					 			 int dn = sc.nextInt();
					 			System.out.println("Enter Topic");
					 			sc.nextLine();
					 			String cptop = sc.nextLine();
					 			System.out.println("Enter Status");
					 			
					 			String cpstat = sc.nextLine();
					 			CoursePlan cpp = new CoursePlan();
					 			
					 			cpp.setBatchid(cpid);
					 			cpp.setDaynumber(dn);
					 			cpp.setTopic(cptop);
					 			cpp.setStatus(cpstat);
					 			
					 			try {
					 				String asn =  ad.addCoursePlan(cpp);
					 				System.out.println(asn);
					 			} catch (CoursePlanException e) {
					 				
					 				e.printStackTrace();
					 			}
					 			  
					 			  
					 			 flag=true;
					 		    	break;
					 		  case 11:
					 			 System.out.println("Enter course plan id"); 
					 			 int pid = sc.nextInt();
					 			
					 			try {
					 				String ans = ad.updateCoursePlan(pid);
					 				System.out.println(ans);
					 			} catch (CoursePlanException e) {
					 				// TODO Auto-generated catch block
					 				e.printStackTrace();
					 			}
					 			  
					 			  
					 			 flag=true;
					 		    	break;
					 		  case 12:
					 			  System.out.println("Details of course plan");
					 			 try {
					 				List<CoursePlan> list = ad.viewCoursePlan();
					 				list.forEach(s -> System.out.println(s));
					 				
					 			} catch (CoursePlanException e) {
					 				// TODO Auto-generated catch block
					 				e.printStackTrace();
					 			}
					 			  
					 			 flag=true;
					 		    	break;
					 		  case 13:
					 			 
					 			  System.out.println("Enter day for day wise plan");
					 			  int day = sc.nextInt();
					 			  
					 			 try {
					 				List<DayWiseDTO> list =  ad.dayWiseUpdateBatch(day);
					 				
					 				list.forEach(s -> System.out.println(s));
					 				
					 			} catch (CoursePlanException e) {
					 				// TODO Auto-generated catch block
					 				e.printStackTrace();
					 			}
					 			  
					 			 flag=true;
					 			  break;
					 		  case 14:
					 			 System.out.println("Details of every batch");
					 			  
					 			 try {
					 				List<BatchDetails> list =  ad.batchWiseDetails();
					 				
					 				list.forEach(s -> System.out.println(s));
					 				
					 			} catch (CoursePlanException e) {
					 				
					 				e.printStackTrace();
					 			}
					 			  
					 			 flag=true;
					 			  break;
					 		  case 15:
					 			  flag = false;
					 			  quit = true;
					 			  System.out.println("Sucessfully logout");
					 			 
					 		}
				 			
				 				
					 		
				 			}
				 		
				 			
						}catch (AdminException e) {
							System.out.println(e.getMessage());
						}
				
				}
			 			
			 		
			 		
				
			}
			else if(choice==2)
			{
				System.out.println("Login as Faculty");
				
				System.out.println("Enter Username:");
				String uname = sc.next();
				
				System.out.println("Enter Password:");
				String pass = sc.next();
				System.out.println();
				
				
				try {
					Faculty faculty= ad.loginfaculty(uname, pass);
					facultyID=faculty.getFacultyid();
					System.out.println("Login Sucessfull...");
			 		System.out.println("Welcome To Faculty :"+faculty.getFacultyname());
			 		System.out.println();
			 		boolean check = true;
			 		while(check) {
			 			System.out.println("=========================================");
			 			System.out.println("Enter the choice"+"\n"+"1. View Course plan"+"\n"+"2. Fill up the day wise planner"+"\n"+"3.Update username/password"+"\n"+"4. Logout");
			 			System.out.println("=========================================");
				 		int ch1 = sc.nextInt();
				 		switch (ch1) {
						case 1: 
							System.out.println("View Course Plan Details");
							System.out.println();
							try {
								
								 
								List<FacultyCourseViewDTO> list = ad.facultyCoursePlanView(facultyID);
								
								list.forEach(s -> System.out.println(s));
								
								
							
								
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
							
							check = true;
							break ;
						
						case 2: {
							String ans = ad.dayWisePlanner();
							System.out.println(ans);
							check = true;				
							break ;				
						}
						case 3: {
							String ans = ad.updateUsernamePasswordFaculty(facultyID);
							 System.out.println(ans);
							 check = true;
							break ;
						}
						case 4: {
							check = false;
							System.out.println("Logout sucessfully....");
							break;
							
						}
						default:
							System.out.println("Enter valid  input");
							check = true;
						}
			 			
			 		}
			 		
			 		
			 		
			 		
			 	
				}catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
				
			}
			
	
		}
	}

}
