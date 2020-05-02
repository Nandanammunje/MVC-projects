package com.libra.queue.Dao;

import com.libra.queue.Entity.DownloadReport;
import com.libra.queue.Entity.UploadReport;

public interface LibraMqDao {

	public void SaveBook(DownloadReport report);
	public void SaveUpload(UploadReport report);
}
