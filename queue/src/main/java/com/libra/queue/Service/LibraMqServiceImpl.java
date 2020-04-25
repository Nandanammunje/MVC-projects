package com.libra.queue.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libra.queue.Dao.LibraMqDao;
import com.libra.queue.Entity.DownloadReport;

@Service
public class LibraMqServiceImpl implements LibraMqService {

	@Autowired
	LibraMqDao dao;
	
	
	@Override
	public void SaveBook(DownloadReport report) {
		// TODO Auto-generated method stub
		
		dao.SaveBook(report);
	}

}
