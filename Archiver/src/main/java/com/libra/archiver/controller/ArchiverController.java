package com.libra.archiver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libra.archiver.Service.ArchiverService;
import com.libra.archiver.entity.DownloadFrequency;

@RestController
@RequestMapping("/Stat")
public class ArchiverController {

	@Autowired
	private ArchiverService service;
	
	@PostMapping("/GetData")
	public List<DownloadFrequency> GetDownloadStat()
	{
		
		
		return service.GetData();
		
	}
	
}
