package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
			
		// create session factory
		SessionFactory factory = new Configuration()
						.configure("hibernate.cfg.xml")
						.addAnnotatedClass(Student.class)
						.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			int studentId = 6;
			
			// now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve student based on the primary key 
			Student myStudent = session.get(Student.class, studentId);
			
			// delete the student
//			System.out.println(myStudent);
//			session.delete(myStudent);
			
			// delete using query
			session
				.createQuery("delete from Student s where s.id='8'")
				.executeUpdate();
			
			// commit the transaction
			session.getTransaction().commit();
			System.out.println(myStudent);
			
			System.out.println("Done!");
			
		} finally {
			factory.close(); 
		}
	}

}
