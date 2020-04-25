package com.libra.queue.Dao;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.libra.queue.Entity.DownloadReport;

@Repository
public class LibraMqDaoImpl implements LibraMqDao {

	@Autowired
	RabbitTemplate template;

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

}
