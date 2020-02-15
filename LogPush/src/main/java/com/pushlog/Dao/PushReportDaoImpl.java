package com.pushlog.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.pushlog.entity.DownloadReport;

public class PushReportDaoImpl implements PushReportDao{

	@Override
	public void save(DownloadReport report) {
		// TODO Auto-generated method stub
		SessionFactory factory=new Configuration().configure("hibernate.cfg.xml")
				               .addAnnotatedClass(DownloadReport.class).buildSessionFactory();
		Session session=factory.getCurrentSession();
		try
		{
		report.setId(0);
		session.beginTransaction();
		session.save(report);
		session.getTransaction().commit();
		session.close();
		factory.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
				
	}

}
