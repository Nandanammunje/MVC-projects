package com.restdata.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restdata.service.FilecontentService;

@RestController
@RequestMapping("/getdata")
public class FilecontentRestController {
     @Autowired
	FilecontentService filecontentservice;
	
	@PostMapping("/getfilename")
	public List<String> FilecontentController()
	{
		
		List<String> filelist=new ArrayList<String>();
		Collections.addAll(filelist,filecontentservice.filecontent());
		
		return filelist;
		
	}
            @PostMapping("/getlastmodifiedtime")
            public String FileModifiedController()
            {
            	return filecontentservice.filelastmodified();
            }
	
	
}
