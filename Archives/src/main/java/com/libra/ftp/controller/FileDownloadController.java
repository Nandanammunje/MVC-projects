package com.libra.ftp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.libra.ftp.service.FileDownloadService;

@Controller

public class FileDownloadController {

	@Autowired
	private FileDownloadService service;
	
	@GetMapping("/")
     public String ServiceStart()
     {
		
		return "index";
     }
	@GetMapping("/list")
	public String GetDocument(ModelMap model)
	{
		
		String s[]=service.GetDocList();
		System.out.print(s.length);
		
		model.addAttribute("names",s);
		
		return "FTP";
	}
	  

}
