package com.http.download.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.http.downoad.Dao.HttpDownloadDao;

@Service
public class HttpDownloadServiceImpl implements HttpDownloadService {
     
	@Autowired
	HttpDownloadDao httpdownloaddao;
	
	@Override
	public void SaveDocument(String pth) {
		httpdownloaddao.SaveDocument(pth);
	}

	@Override
	public String[] GetDocList() {
		return httpdownloaddao.GetDocList();
}
}