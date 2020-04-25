package com.libra.ftp.Dao;

public interface FileDownloadDao {

	public String[] GetDocList();
	public String[] GetCacheData();
	public void CacheClear();
	public void GetDoc(String filename);
	public void PushLibraArchiva(String filename);
	public void PushLibraUpFile(String filename);
	
}
