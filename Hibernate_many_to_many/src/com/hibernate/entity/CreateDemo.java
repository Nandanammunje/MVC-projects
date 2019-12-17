package com.hibernate.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {
	public static void main(String[] args)
	{
		SessionFactory factory=new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		Session session=factory.getCurrentSession();
		
		try
		{
			
			int Id=2;
			session.beginTransaction();
			InstructorDetail tempInstructordetail=session.get(InstructorDetail.class,Id);
			System.out.println(tempInstructordetail);
			System.out.println(tempInstructordetail.getInstructor());
			
		}
		catch(Exception e)
		{
			
		}
	}
}
