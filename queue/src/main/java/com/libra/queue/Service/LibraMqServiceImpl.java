package com.libra.queue.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libra.queue.Dao.LibraMqDao;
import com.libra.queue.Entity.DownloadReport;
import com.libra.queue.Entity.UploadReport;

@Service
public class LibraMqServiceImpl implements LibraMqService {

	@Autowired
	LibraMqDao dao;
	
	
	@Override
	public void SaveBook(DownloadReport report) {
		// TODO Auto-generated method stub
		
		dao.SaveBook(report);
	}


	@Override
	public void SaveUpload(UploadReport report) {
		// TODO Auto-generated method stub
		dao.SaveUpload(report);
	}

}
