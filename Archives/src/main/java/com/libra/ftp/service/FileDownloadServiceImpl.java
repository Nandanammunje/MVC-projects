package com.libra.ftp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libra.ftp.Dao.FileDownloadDao;

@Service
public class FileDownloadServiceImpl implements FileDownloadService
{
	@Autowired
	FileDownloadDao filedownloaddao;

	@Override
	public String[] GetDocList() {
		// TODO Auto-generated method stub
		return filedownloaddao.GetDocList();
	}

}
