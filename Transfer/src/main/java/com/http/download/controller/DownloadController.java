package com.http.download.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.http.download.service.HttpDownloadService;

@Controller
public class DownloadController {
  @Autowired
  HttpDownloadService service;
	
	@GetMapping("/")
	public String GetDocument()
	{
		service.SaveDocument();
		
		return "index";
	}
	
	
	
	
}
