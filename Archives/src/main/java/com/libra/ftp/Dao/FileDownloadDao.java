package com.libra.ftp.Dao;

public interface FileDownloadDao {

	public String[] GetDocList();
	public String[] GetCacheData();
	public void CacheClear();
	
}
