package com.http.download.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.http.download.entity.Pathfinder;
import com.http.download.service.HttpDownloadService;

@Controller

public class DownloadController {
  @Autowired
  HttpDownloadService service;
	@GetMapping("/")
	public String start()
	{
		return "redirect:/list";
	}
	@GetMapping("/list")
	public String GetDocument(Model model)
	{
		Pathfinder find=new Pathfinder();
		String s[]=service.GetDocList();
		System.out.print(s.length);
		model.addAttribute("pathfinder",find);
		model.addAttribute("service",s);
		
		return "FTP";
	}
	 @GetMapping("/getdoc")
	public String Fetch(@ModelAttribute("pathfinder") Pathfinder path)
	{
		service.SaveDocument(path.getPath());
		
		return "index";
	}
	
	
}
