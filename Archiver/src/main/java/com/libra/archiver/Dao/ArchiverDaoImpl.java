package com.libra.archiver.Dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.libra.archiver.entity.DownloadFrequency;

/**
 * @author nandannayak
 *
 */
@Repository
public class ArchiverDaoImpl implements ArchiverDao {
     @Autowired
	private SessionFactory factory;
	
	
	@Override
	public List<DownloadFrequency> GetData() {
		// TODO Auto-generated method stub
		Session session=factory.getCurrentSession();
	     Query query=session.createQuery("from DownloadFrequency s where s.frequency > 1");	
	     List<DownloadFrequency> frequency=query.getResultList();
		
		return frequency;
	}

}
