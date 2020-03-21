package com.pushlog.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.pushlog.entity.DownloadReport;
import com.pushlog.entity.UploadReport;

public class PushReportDaoImpl implements PushReportDao{

	@Override
	public void save(Object table,String tablename) {
		// TODO Auto-generated method stub
		SessionFactory factory=new Configuration().configure("hibernate.cfg.xml")
				               .addAnnotatedClass(DownloadReport.class).addAnnotatedClass(UploadReport.class).
				               buildSessionFactory();
		DownloadReport report = null;
		UploadReport record=null;
		Session session=factory.getCurrentSession();
		try
		{
		if(tablename.equals("downloadreport"))
		{
	      report=(DownloadReport)table;
	      report.setId(0);
	      session.beginTransaction();
		  session.save(report);
		 
		}
		else
		{
			record=(UploadReport)table;
			record.setId(0);
			session.beginTransaction();
			session.save(record);
			
		}
		
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
