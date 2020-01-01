package com.http.download.Dao;

public interface HttpDownloadDao {

	public void SaveDocument(String pth);
	public String[] GetDocList();
}
