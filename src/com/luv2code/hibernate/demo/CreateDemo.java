package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {

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
			
			// create the objects
			Instructor tempInstructor = 
					new Instructor("Chad1", "Darby", "darbyluv2code.com");
			
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail(
							"http://www.luv2code.com","coding and eating");
			
			// associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			// save the instructor
			session.save(tempInstructor);
			
			// note: tempInstructorDetail will also be saved in the database due to cascade setup
			
			// commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} finally {
			factory.close(); 
		}
	}

}
