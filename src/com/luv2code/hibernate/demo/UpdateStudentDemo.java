package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
						.configure("hibernate.cfg.xml")
						.addAnnotatedClass(Student.class)
						.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			int studentId = 2;
			
			// now get a new session and start transaction
			session = factory.getCurrentSession();
			session
				.beginTransaction();
			
			// retrieve student based on the id: primary key
			System.out.println("\nGetting student with id: " + studentId);
			Student myStudent = session.get(Student.class, studentId);
			
			System.out.println(myStudent);
			myStudent.setFirstName("Scooby3");// update student firstName value
			System.out.println(myStudent);
			
			// commit the transaction
			session.getTransaction().commit();
			
			// NEW code
			
			// now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// write a query for bulk update 
			session
				.createQuery("update Student set email='foo@gmail.com'")
				.executeUpdate();
			
			session
				.createQuery("update Student s set email='foo@gmail2.com',first_name='betty' where s.id='1'")
				.executeUpdate();
			
			Student myStudent2 = session.get(Student.class, 1);
			System.out.println(myStudent2);
			
			// commit the transaction
			session.getTransaction().commit();
			
			
			System.out.println("Done!");
			
		} finally {
			factory.close(); 
		}
	}

}
