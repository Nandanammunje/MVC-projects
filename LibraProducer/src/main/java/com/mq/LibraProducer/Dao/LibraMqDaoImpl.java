package com.mq.LibraProducer.Dao;

import static com.mq.LibraProducer.Constants.Constants.DOWNLOADTYPE;
import static com.mq.LibraProducer.Constants.Constants.LIBRADBPUSHURL;
import static com.mq.LibraProducer.Constants.Constants.LIBRADBSVCPUSHDOWNLOADREPORT;
import  static com.mq.LibraProducer.Constants.Constants.LOGEXT;
import static com.mq.LibraProducer.Constants.Constants.MQEXCHANGE;
import static com.mq.LibraProducer.Constants.Constants.UPLOADTYPE;
import static com.mq.LibraProducer.Constants.Constants.WEBSVCLOGLOCATION;
import static com.mq.LibraProducer.Constants.Constants.WEBSVCLOGPATTERN;
import static com.mq.LibraProducer.Constants.Constants.WEBSVCLOGUPLOAD;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mq.LibraProducer.entity.DownloadReport;
import com.mq.LibraProducer.entity.UploadReport;

@SuppressWarnings("deprecation")
@Repository
public class LibraMqDaoImpl implements LibraMqDao {

	@Autowired
	RabbitTemplate template;

	


	
	
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
			path =WEBSVCLOGUPLOAD + "-" + formatlog.format(new Date()) + LOGEXT;
		else
			path =WEBSVCLOGLOCATION + "-" + formatlog.format(new Date()) + LOGEXT;
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

	

	@Override
	public void SaveRecord(Object Record, String RecordType) {
		// TODO Auto-generated method stub
		DownloadReport report=null;
		UploadReport record=null;
		String json=null;
		ObjectMapper map=new ObjectMapper();
		if(RecordType.equals(DOWNLOADTYPE))
		{
		 report=(DownloadReport) Record;
		 try
		 {
			 json=map.writeValueAsString(report);
		 }
			catch(JsonProcessingException e)
		 {
				e.getMessage();
		 }
		 
		}
		else
		{
			record=(UploadReport) Record;
			try {
				json=map.writeValueAsString(record);
				
			}
			catch(JsonProcessingException e)
			{
				e.getMessage();
			}
		}
		template.convertAndSend(MQEXCHANGE,RecordType,json);
	}

	

}
