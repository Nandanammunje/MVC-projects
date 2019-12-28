package com.restdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restdata.dao.FilecontentDao;

@Service
public class FilecontentServiceImpl implements FilecontentService {

	@Autowired
	FilecontentDao filecontentdao;
	
	@Override
	public String[] filecontent() {
		// TODO Auto-generated method stub
		return filecontentdao.filecontent();
	}

}
