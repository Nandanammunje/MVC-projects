package com.libra.queue.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.libra.queue.Entity.DownloadReport;
import com.libra.queue.Service.LibraMqService;

@Controller
@RequestMapping("/queue")
public class LibraMqController {

	@Autowired
	LibraMqService service;

	@PostMapping("/push")
	public void Save(@RequestBody DownloadReport report) {
              service.SaveBook(report);
	}

}
