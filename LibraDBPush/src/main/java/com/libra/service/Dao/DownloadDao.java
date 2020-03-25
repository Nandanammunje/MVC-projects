package com.libra.service.Dao;

import com.libra.service.entity.DownloadFrequency;
import com.libra.service.entity.Downloadreport;
import com.libra.service.entity.UploadReport;

public interface DownloadDao {
	
	public void Save(Downloadreport report);
	public void Save(UploadReport report);
	public void Save(DownloadFrequency report);
       
}
