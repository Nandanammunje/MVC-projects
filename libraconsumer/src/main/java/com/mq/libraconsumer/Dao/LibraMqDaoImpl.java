package com.mq.libraconsumer.Dao;

import static com.mq.libraconsumer.constants.Constants.DOWNLOADTYPE;
import static com.mq.libraconsumer.constants.Constants.LIBRADBPUSHURL;
import static com.mq.libraconsumer.constants.Constants.LIBRADBSVCPUSHDOWNLOADREPORT;
import static com.mq.libraconsumer.constants.Constants.LOGEXT;
import static com.mq.libraconsumer.constants.Constants.UPLOADTYPE;
import static com.mq.libraconsumer.constants.Constants.WEBSVCLOGLOCATION;
import static com.mq.libraconsumer.constants.Constants.WEBSVCLOGPATTERN;
import static com.mq.libraconsumer.constants.Constants.WEBSVCLOGUPLOAD;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.AsyncRestTemplate;


@SuppressWarnings("deprecation")
@Repository
public class LibraMqDaoImpl  {

	@Autowired
	RabbitTemplate template;

	@SuppressWarnings("deprecation")
	public void SaveDBDownloadSvc(String jsondata) {
		System.out.println(jsondata);
		AsyncRestTemplate rest = new AsyncRestTemplate();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<String>(jsondata, header);
		
		try {
			 int statuscode = rest.exchange(LIBRADBSVCPUSHDOWNLOADREPORT,HttpMethod.POST, requestEntity, String.class).get().getStatusCodeValue();
			}
		catch(Exception e)
		{
			LogWrite(jsondata,DOWNLOADTYPE);
		}

	}


	@RabbitListener(queues = "libradownload")
	public void PushDownloadData(String message) {

		SaveDBDownloadSvc(message);

	}

	@RabbitListener(queues = "libraupload")
	public void PushUploadData(String message) {

		System.out.println(message);
		SaveDBUploadSvc(message);
	}
    
	@SuppressWarnings("deprecation")
	public void SaveDBUploadSvc(String jsondata) {
		AsyncRestTemplate rest = new AsyncRestTemplate();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(jsondata, header);

		try {
			int statuscode = rest.exchange(LIBRADBPUSHURL, HttpMethod.POST, requestEntity, String.class).get()
					.getStatusCodeValue();
		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			LogWrite(jsondata, UPLOADTYPE);
		}

	}
	

	public void LogWrite(String jsondata, String Logtype) {
		SimpleDateFormat formatlog = new SimpleDateFormat(WEBSVCLOGPATTERN);
		String path;
		if (Logtype.equals(UPLOADTYPE))
			path = WEBSVCLOGUPLOAD + "-" + formatlog.format(new Date()) + LOGEXT;
		else
			path = WEBSVCLOGLOCATION + "-" + formatlog.format(new Date()) + LOGEXT;
		File file = new File(path);
		FileWriter fr;
		BufferedWriter br;
		try {
			fr = new FileWriter(file, true);

			br = new BufferedWriter(fr);

			br.write(jsondata + "|");
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	

	

	

}
