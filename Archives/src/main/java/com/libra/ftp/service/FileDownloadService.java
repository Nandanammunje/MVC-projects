package com.libra.ftp.service;

public interface FileDownloadService {
	
	public String[] GetDocList();
	public String[] GetCacheData();
	public void CacheClear();
	public void GetDoc(String filename);

}
