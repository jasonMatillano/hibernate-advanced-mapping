package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
						.configure("hibernate.cfg.xml")
						.addAnnotatedClass(Student.class)
						.buildSessionFactory();
		
		// create session
		Session  session = factory.getCurrentSession();
		
		try {
			// create a student object
			System.out.println("Creating a new student object...");
			Student tempStudent = new Student("Micky", "Mouse", "jasluv2code.com");
			
			// start transaction
			System.out.println("Starting session...");
			System.out.println(tempStudent);
			session.beginTransaction();			
			
			// save the student object
			System.out.println("id before session save: " + tempStudent.getId());
			System.out.print("session.save>>");
			session.save(tempStudent);
			System.out.println("id after session save: " + tempStudent.getId());
			System.out.println(tempStudent);
			// commit the transaction
			System.out.println("Commiting transaction...");
			session.getTransaction().commit();
			
			// NEW CODE
			
			// find out the student's id: primary key
			System.out.println("Saved student. Generated id: " + tempStudent.getId());
			
			// now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve student based on the id: primary key
			System.out.println("\nGetting student with id: " + tempStudent.getId());
			System.out.print("Retrieving student via id>>");
			Student myStudent = session.get(Student.class, tempStudent.getId());
			System.out.println("Get complete: " + myStudent);
			
			// commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} finally {
			factory.close(); 
		}
	}

}
