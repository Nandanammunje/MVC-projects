package com.libra.service.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libra.service.Dao.DownloadDao;
import com.libra.service.entity.Downloadreport;
import com.libra.service.entity.UploadReport;
@Service
public class DownloadReportServiceImpl  implements DownloadReportService {

	@Autowired
	public DownloadDao dao;
	
	@Transactional
	@Override
	public void Save(Downloadreport report) {
		// TODO Auto-generated method stub
		dao.Save(report);
	}
    @Transactional
	@Override
	public void Save(UploadReport report) {
		// TODO Auto-generated method stub
		dao.Save(report);
	}

}
