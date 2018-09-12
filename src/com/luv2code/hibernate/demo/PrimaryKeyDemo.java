package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
						.configure("hibernate.cfg.xml")
						.addAnnotatedClass(Student.class)
						.buildSessionFactory();
		
		// create session
		Session  session = factory.getCurrentSession();
		
		try {
			// create 3 student objects
			System.out.println("Creating 3 new student objects...");
			Student tempStudent1 = new Student("John", "Doe", "jonluv2code.com");
			Student tempStudent2 = new Student("Mary", "Public", "maryluv2code.com");
			Student tempStudent3 = new Student("Bonita", "Wally", "bonitaluv2code.com");
			
			// start transaction
			System.out.println("Starting session...");
			session.beginTransaction();			
			
			// id value before session save
			System.out.println("Id values before session save:");
			System.out.println(tempStudent1.getId());
			System.out.println(tempStudent2.getId());
			System.out.println(tempStudent3.getId());
			System.out.println("");
			
			// save the student object
			System.out.println("Saving the student...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			
			// id value before session save
			System.out.println("\nId values after session save:");
			System.out.println(tempStudent1.getId());
			System.out.println(tempStudent2.getId());
			System.out.println(tempStudent3.getId());
			System.out.println("");
			
			// commit the transaction
			System.out.println("Commiting transaction...");
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} finally {
			factory.close(); 
		}

	}

}
