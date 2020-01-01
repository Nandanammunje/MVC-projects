package com.http.download.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.http.download.entity.Pathfinder;
import com.http.download.service.HttpDownloadService;

@Controller
public class DownloadController {
  @Autowired

  HttpDownloadService service;
	
	@GetMapping("/")
	public String GetDocument(Model model)
	{
		Pathfinder find=new Pathfinder();
		for(String s:service.GetDocList())
			
		model.addAttribute("pathfinder",find);
		return "FTP";
	}
	 @GetMapping("/getdoc")
	public String Fetch(@ModelAttribute("pathfinder") Pathfinder path)
	{
		service.SaveDocument(path.getPath());
		
		return "index";
	}
	
	
}
