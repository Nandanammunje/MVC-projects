package com.mq.LibraProducer.Dao;

import static com.mq.LibraProducer.Constants.Constants.DOWNLOADTYPE;
import static com.mq.LibraProducer.Constants.Constants.MQEXCHANGE;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mq.LibraProducer.entity.DownloadReport;
import com.mq.LibraProducer.entity.UploadReport;


@Repository
public class LibraMqDaoImpl implements LibraMqDao {

	@Autowired
	RabbitTemplate template;

	@Override
	public void SaveRecord(Object Record, String RecordType) {
		// TODO Auto-generated method stub
		DownloadReport report = null;
		UploadReport record = null;
		String json = null;
		ObjectMapper map = new ObjectMapper();
		if (RecordType.equals(DOWNLOADTYPE)) {
			report = (DownloadReport) Record;
			try {
				json = map.writeValueAsString(report);
			} catch (JsonProcessingException e) {
				e.getMessage();
			}

		} else {
			record = (UploadReport) Record;
			try {
				json = map.writeValueAsString(record);

			} catch (JsonProcessingException e) {
				e.getMessage();
			}
		}
		template.convertAndSend(MQEXCHANGE, RecordType, json);
	}

}
