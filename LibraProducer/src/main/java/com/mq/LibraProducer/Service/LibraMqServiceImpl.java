package com.mq.LibraProducer.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mq.LibraProducer.Dao.LibraMqDao;

@Service
public class LibraMqServiceImpl implements LibraMqService {

	@Autowired
	LibraMqDao dao;

	@Override
	public void SaveRecord(Object Record, String RecordType) {
		// TODO Auto-generated method stub
		dao.SaveRecord(Record, RecordType);
	}

}
