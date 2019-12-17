package com.hibernate.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourses {
	public static void main(String[] args)
	{
		SessionFactory factory=new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		Session session=factory.getCurrentSession();
		try
		{       session.beginTransaction();
		int Id=1;
		Instructor tempInstructor=session.get(Instructor.class,Id);
		Course tempcourse1=new Course("Computer Science"); 	
		Course tempCourse2=new Course("Mathematics");
		tempInstructor.add(tempcourse1);
		tempInstructor.add(tempCourse2);
		session.save(tempcourse1);
		session.save(tempCourse2);
		
     session.getTransaction().commit();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally
		{
			factory.close();
		}
	
	}
}
