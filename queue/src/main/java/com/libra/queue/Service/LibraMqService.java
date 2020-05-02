package com.libra.queue.Service;

import com.libra.queue.Entity.DownloadReport;
import com.libra.queue.Entity.UploadReport;

public interface LibraMqService {

	public void SaveBook(DownloadReport report);
	public void SaveUpload(UploadReport report);
}
