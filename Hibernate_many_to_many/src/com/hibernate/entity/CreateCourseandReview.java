
package com.hibernate.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseandReview {
	public static void main(String[] args)
	{
		SessionFactory factory=new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		Session session=factory.getCurrentSession();
		try
		{       session.beginTransaction();
		
		Course tempCourse=new Course("Machine Learning");
		tempCourse.addReview(new Review("It was very educative"));
		tempCourse.addReview(new Review("Instructor was very fast"));
		tempCourse.addReview(new Review("it was Satifactory"));
		session.save(tempCourse);
		
		
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
