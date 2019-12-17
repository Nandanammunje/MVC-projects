
package com.hibernate.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseandStudents {
	public static void main(String[] args)
	{
		SessionFactory factory=new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		Session session=factory.getCurrentSession();
		try
		{       session.beginTransaction();
		
		Course tempCourse=new Course("Machine Learning");
		
		Student tempStudent=new Student("nandan","nayak","nandannayak15@gmail.com");
		Student tempStudent1=new Student("Prakhyath","Shenoy","PrakhyathShenoy76@gmail.com");
		session.save(tempCourse);
		tempCourse.addStudent(tempStudent);
		tempCourse.addStudent(tempStudent1);
		session.save(tempStudent);
		session.save(tempStudent1);
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
