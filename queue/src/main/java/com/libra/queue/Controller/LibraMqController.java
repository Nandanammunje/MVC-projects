package com.libra.queue.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libra.queue.Entity.DownloadReport;
import com.libra.queue.Service.LibraMqService;

@RestController
@RequestMapping("/queue")
public class LibraMqController {

	@Autowired
	LibraMqService service;

	@PostMapping("/push")
	public DownloadReport Save(@RequestBody DownloadReport report) {
              service.SaveBook(report);
              return report;
	}

}
