package com.mq.LibraProducer.Controller;

import static com.mq.LibraProducer.Constants.Constants.DOWNLOADTYPE;
import static com.mq.LibraProducer.Constants.Constants.UPLOADTYPE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mq.LibraProducer.Service.LibraMqService;
import com.mq.LibraProducer.entity.DownloadReport;
import com.mq.LibraProducer.entity.UploadReport;

@RestController
@RequestMapping("/queue")
public class LibraMqController {

	@Autowired
	LibraMqService service;

	@PostMapping("/push")
	public DownloadReport Save(@RequestBody DownloadReport report) {
		service.SaveRecord(report, DOWNLOADTYPE);
		return report;
	}

	@PostMapping("/upload")
	public UploadReport Save(@RequestBody UploadReport report) {
		service.SaveRecord(report, UPLOADTYPE);
		return report;
	}

}
