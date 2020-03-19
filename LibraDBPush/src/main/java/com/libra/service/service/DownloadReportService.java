package com.libra.service.service;

import com.libra.service.entity.Downloadreport;
import com.libra.service.entity.UploadReport;

public interface DownloadReportService {
	public void Save(Downloadreport report);
	public void Save(UploadReport report);
}
