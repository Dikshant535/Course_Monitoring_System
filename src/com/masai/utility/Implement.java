package com.masai.utility;

import java.rmi.ConnectIOException;
import java.sql.Connection;

public class Implement {
	
	
	public static void main(String[] args) {
		
		Connection conn = DButil.provideConnection();
		
		if(conn!=null) {
			System.out.println("I love you.....");
			
		}
		
		
	}
	
}
