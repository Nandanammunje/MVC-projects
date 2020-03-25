package com.libra.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.libra.service.entity.DownloadFrequency;
import com.libra.service.entity.Downloadreport;
import com.libra.service.entity.UploadReport;
import com.libra.service.service.DownloadReportService;

@RestController
public class DownloadReportController {

	
	@Autowired
	public DownloadReportService service;
	
	@PostMapping("/savereport")
	public int SaveReport(@RequestBody Downloadreport report)
	{
		DownloadFrequency frequency=new DownloadFrequency();
		report.setId(0);
		System.out.println(report.getLogtime());
		service.Save(report);
		frequency.setName(report.getName());
		service.Save(frequency);
		return 1;
	}
	
	@PostMapping("/saverecord")
	public int SaveRecord(@RequestBody UploadReport report)
	{
		report.setId(0);
		System.out.println(report.getLogtime());
		service.Save(report);
		return 1;
	}
	
}
