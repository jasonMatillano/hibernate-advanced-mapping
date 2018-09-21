	package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;

public class AE_DeleteInstructorDetailOnlyDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
						.configure("hibernate.cfg.xml")
						.addAnnotatedClass(Instructor.class)
						.addAnnotatedClass(InstructorDetail.class)
						.addAnnotatedClass(Course.class)
						.addAnnotatedClass(Review.class)
						.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// start transaction
			session.beginTransaction();			
			
			// get instructor detail object
			int theID = 1;
			InstructorDetail tempInsDetail = 
					session.get(InstructorDetail.class, theID);
			
			// print instructor detail
			System.out.println("luv2code to delete : " + tempInsDetail);
			
			// print the associated instructor
			System.out.println( "luv2code to unlink : " + tempInsDetail.getInstructor());
			
			// remove the link before delete
			tempInsDetail.getInstructor().setInstructorDetail(null);
			
			// delete instructor detail
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
