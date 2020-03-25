package com.libra.service.Dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.libra.service.entity.DownloadFrequency;
import com.libra.service.entity.Downloadreport;
import com.libra.service.entity.UploadReport;
@Repository
public class DownloadDaoImpl implements DownloadDao {
	
	
	@Autowired
	private SessionFactory factory;

	@Override
	public void Save(Downloadreport report) {
		// TODO Auto-generated method stub
	Session session =factory.getCurrentSession();
	
	
	session.saveOrUpdate(report);
	
	}

	@Override
	public void Save(UploadReport report) {
		// TODO Auto-generated method stub
		Session session=factory.getCurrentSession();
		session.save(report);
	}

	@Override
	public void Save(DownloadFrequency report) {
		Session session=factory.getCurrentSession();
	    DownloadFrequency bookstat;
		Query query=session.createQuery("from DownloadFrequency s where s.name =:name");
		
		query.setParameter("name",report.getName());
		List<DownloadFrequency>frequency=query.getResultList();
		if(frequency==null||frequency.size()==0)
		{
			bookstat=new DownloadFrequency();
		  bookstat.setFrequency(1);
		  bookstat.setName(report.getName());
		  bookstat.setId(0);
		  session.saveOrUpdate(bookstat);
		}
		else
		{
			bookstat=frequency.get(0);
			bookstat.setFrequency(bookstat.getFrequency()+1);
			session.saveOrUpdate(bookstat);
		}
		
		// TODO Auto-generated method stub
		
	}

	
	

}
