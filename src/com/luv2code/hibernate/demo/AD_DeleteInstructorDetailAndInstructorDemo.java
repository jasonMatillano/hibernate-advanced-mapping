	package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class AD_DeleteInstructorDetailAndInstructorDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
						.configure("hibernate.cfg.xml")
						.addAnnotatedClass(Instructor.class)
						.addAnnotatedClass(InstructorDetail.class)
						.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// start transaction
			session.beginTransaction();			
			
			// get instructor detail object
			int theID = 2;
			InstructorDetail tempInsDetail = 
					session.get(InstructorDetail.class, theID);
			
			// print instructor detail
			System.out.println("luv2code to delete : " + tempInsDetail);
			
			// print the associated instructor
			 System.out.println("luv2code to delete : " + tempInsDetail.getInstructor());
			
			// delete instructor detail along with instructor
			session.delete(tempInsDetail);
			
			// commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} 
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			// handle connection leaks
			session.close();
			
			factory.close(); 
		}
	}

}