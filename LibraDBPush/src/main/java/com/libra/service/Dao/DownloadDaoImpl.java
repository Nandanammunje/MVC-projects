package com.libra.service.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.libra.service.entity.Downloadreport;
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

}
