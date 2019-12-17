package com.hibernate.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructor {
	public static void main(String[] args)
	{
		SessionFactory factory=new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		Session session=factory.getCurrentSession();
		try
		{       session.beginTransaction();
	            Instructor tempInstructor=new Instructor("Alan","Turing","AlanTuring@gmail.com");
	            InstructorDetail tempInstructorDetail=new InstructorDetail("Real-Engineering.com","Computer Science");
	            tempInstructor.setInstructorDetail(tempInstructorDetail);
	            session.save(tempInstructor);
	            session.getTransaction().commit();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	
	}
}
