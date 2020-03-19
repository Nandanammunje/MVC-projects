package com.libra.service.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

}
