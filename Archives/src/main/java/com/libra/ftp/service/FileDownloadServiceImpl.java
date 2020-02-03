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

	@Override
	public String[] GetCacheData() {
		// TODO Auto-generated method stub
	  return filedownloaddao.GetCacheData();
	}

	@Override
	public void CacheClear() {
		// TODO Auto-generated method stub
		filedownloaddao.CacheClear();
	}

	@Override
	public void GetDoc(String filename) {
		// TODO Auto-generated method stub
		filedownloaddao.GetDoc(filename);
	}
	

}
