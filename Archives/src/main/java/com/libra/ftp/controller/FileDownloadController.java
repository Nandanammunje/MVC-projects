package com.libra.ftp.controller;

import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.libra.ftp.Entity.PathFinder;
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
		PathFinder path=new PathFinder();
		String s[]=service.GetDocList();
	    model.addAttribute("names",s);
		model.addAttribute("docname",path);
		return "FTP";
	}
	
	@GetMapping("/sac")
    public String CachingConsole()
    {
	     return "JCS";	
     }
	
	
	  @GetMapping("/viewcache")
	  public String GetCachedProperties(ModelMap modelmap)
	  {
		  String s[]=service.GetCacheData();
		  if(s==null || s.length==0)
		  {
			  s[0]="cache is empty";
		  }
		  modelmap.addAttribute("cachedproperties",s);
		  return "SAC";
	  }
	  
	  @GetMapping("/clearcache")
	  public String GetCacheClear()
	  {
		  service.CacheClear();
		  return "redirect:/sac";
		  
	  }
	  @PostMapping("/savedocument")
	  public String Archive(@ModelAttribute("docname") PathFinder find)
	  {
		  String filename=find.getPath();
		 System.out.println(filename);
		  service.GetDoc(filename);
		  return "index";
	  }
	  @GetMapping("/upload")
	  public String fileupload()
	  {
		  return "FileUpload";
	  }
	  
	  @PostMapping("/UploadDoc")
	  public String SaveUploadedDoc(@RequestParam("file") MultipartFile file,Model model) throws IOException
	  {
		 
		 FileOutputStream out=new FileOutputStream("C:/Gate-ECE/"+file.getOriginalFilename());
		 
		  out.write(file.getBytes());
		  out.close();
		  service.CacheClear();
		  return "redirect:/list";
	  }

}
