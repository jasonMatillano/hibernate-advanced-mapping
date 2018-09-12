package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
						.configure("hibernate.cfg.xml")
						.addAnnotatedClass(Student.class)
						.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// start transaction
			System.out.println("Starting session...");
			session.beginTransaction();			
			
			// create a student object
			System.out.println("Creating a new student object...");
			Student tempStudent = new Student("Jason1", "Wall", "jasluv2code.com");
			System.out.println("Student value before save: " + tempStudent);
			
			// save the student object
			System.out.println("Saving the student...");
			session.save(tempStudent);
			System.out.println("Student value after save: " + tempStudent);
			
			// commit the transaction
			System.out.println("Commiting transaction...");
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} finally {
			factory.close(); 
		}
	}

}
