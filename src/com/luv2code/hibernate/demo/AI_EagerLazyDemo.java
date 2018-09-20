package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class AI_EagerLazyDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
						.configure("hibernate.cfg.xml")
						.addAnnotatedClass(Instructor.class)
						.addAnnotatedClass(InstructorDetail.class)
						.addAnnotatedClass(Course.class)
						.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// start transaction
			session.beginTransaction();
			
			// get instructor from db
			int theId = 2;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			// print instructor
			System.out.println("luv2code: tempInstructor: " + tempInstructor);
			System.out.println("luv2code: tempInstructorCourses: " + tempInstructor.getCourses());
			
			// commit the transaction
			session.getTransaction().commit();
			
			// close session
			session.close();
			System.out.println("luv2code: The seesion is closed:");
			
			// option 1: call a getter method while the session is open
			
			// get course for the instructor
			System.out.println("luv2code: tempInstructorCourses: " + tempInstructor.getCourses());
				
			System.out.println("luv2code: Done!");
			
		} finally {
			
			// add clean up code
			session.close();
			
			factory.close(); 
		}
	}
}