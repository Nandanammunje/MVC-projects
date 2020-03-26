package com.pushlog.Dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.pushlog.entity.DownloadFrequency;
import com.pushlog.entity.DownloadReport;
import com.pushlog.entity.UploadReport;

public class PushReportDaoImpl implements PushReportDao{

	@Override
	public void save(Object table,String tablename) {
		// TODO Auto-generated method stub
		SessionFactory factory=new Configuration().configure("hibernate.cfg.xml")
				               .addAnnotatedClass(DownloadReport.class).addAnnotatedClass(UploadReport.class).
				                addAnnotatedClass(DownloadFrequency.class).
				               buildSessionFactory();
		DownloadReport report = null;
		UploadReport record=null;
		DownloadFrequency stat=null;
		Session session=factory.getCurrentSession();
		try
		{
		if(tablename.equals("downloadreport"))
		{
	      report=(DownloadReport)table;
	      report.setId(0);
	      session.beginTransaction();
		  session.save(report);
		    Query query=session.createQuery("from DownloadFrequency s where s.name =:name ");
		    query.setParameter("name",report.getName());
		      List<DownloadFrequency> statrecord=query.getResultList();
		    if(statrecord==null||statrecord.size()==0)
		    {
		    	stat=new DownloadFrequency();
		    	stat.setFrequency(1);
		    	stat.setName(report.getName());
		    	stat.setId(0);
		    	session.save(stat);
		    }
		    else
		    {
		    	stat=statrecord.get(0);
		    	stat.setFrequency(stat.getFrequency()+1);
		    	session.saveOrUpdate(stat);
		    }
		 
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
