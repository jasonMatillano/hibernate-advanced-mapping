package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;



public class QueryStudentDemo {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
						.configure("hibernate.cfg.xml")
						.addAnnotatedClass(Student.class)
						.buildSessionFactory();
		
		// create session
		Session  session = factory.getCurrentSession();
		
		try {

			
			// start transaction
			System.out.println("Starting session...");
			session.beginTransaction();			
			
			// query students
			List<Student> theStudents = session.createQuery("from Student").list();
			
			// display the students
			displayStudents(theStudents);
			
			// query student with lastname=Doe
			theStudents = session.createQuery("from Student s where s.lastName='Doe'").list();
			
			//display Doe
			System.out.println("\nDisplay all Doe:");
			displayStudents(theStudents);
			
			// query student with lastname=Doe
			theStudents = session.createQuery("from Student s where" + 
												" s.lastName='Doe' OR s.lastName='Public'").list();
			
			//display Doe and Public
			System.out.println("\nDisplay all Doe and Public:");
			displayStudents(theStudents);
			
			// query students where email LIKE '%luv2code.com'
			theStudents = session.createQuery("from Student s where" + 
												" s.email LIKE '%jonluv2code.com'").list();
			
			//display Doe and Public
			System.out.println("\nDisplay all email LIKE '%luv2code.com'");
			displayStudents(theStudents);
			
			// commit the transaction
			System.out.println("Commiting transaction...");
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} finally {
			factory.close(); 
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent : theStudents ) {
			System.out.println(tempStudent);
		}
	}

}
