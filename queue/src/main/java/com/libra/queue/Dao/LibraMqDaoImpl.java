package com.libra.queue.Dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.libra.queue.Constants.Constants;
import com.libra.queue.Entity.DownloadReport;

@SuppressWarnings("deprecation")
@Repository
public class LibraMqDaoImpl implements LibraMqDao {

	@Autowired
	RabbitTemplate template;

	// @Autowired
	// private EntityManager entitymanager;

	@Override
	public void SaveBook(DownloadReport report) {
		// TODO Auto-generated method stub
		ObjectMapper jsonparser = new ObjectMapper();
		String JsonString = null;
		try {
			JsonString = jsonparser.writeValueAsString(report);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		template.convertAndSend("libradownload", JsonString);

	}

	@RabbitListener(queues = "libradownload")
	public void PushData(String message) {

		
		SaveDBSvc(message);

	}

	@SuppressWarnings("deprecation")
	public void SaveDBSvc(String jsondata) {
		System.out.println(jsondata);
		AsyncRestTemplate rest = new AsyncRestTemplate();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<String>(jsondata, header);
		ListenableFuture<ResponseEntity<String>> future = rest.exchange(Constants.LIBRADBSVCPUSHDOWNLOADREPORT,
				HttpMethod.POST, requestEntity, String.class);
		try {
			if (future.get().getStatusCodeValue() != 200) {
				SimpleDateFormat formatlog = new SimpleDateFormat(Constants.WEBSVCLOGPATTERN);
				String path = Constants.WEBSVCLOGLOCATION + "-" + formatlog.format(new Date()) + Constants.LOGEXT;
				File file = new File(path);
				FileWriter fr;
				BufferedWriter br;
				fr = new FileWriter(file, true);

				br = new BufferedWriter(fr);

				br.write(jsondata + "|");
				br.close();
				fr.close();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
