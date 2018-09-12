package com.luv2code.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		
		String jdbcurl = "jdbc:mysql://localhost:3310/hb-01-one-to-one-uni?useSSL=false&serverTimezone=UTC";
		String user = "root";
		String pass = "1234";
		
		try {
			System.out.println("Connecting to DB...");
			
			Connection myConn = DriverManager.getConnection(jdbcurl, user, pass);
			
			System.out.println("Connecting SUCESS!");
			System.out.println("Connection name: " + myConn);
			
			
		} catch (Exception exc) {
			exc.printStackTrace();
			
		}
	}

}
