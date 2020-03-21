package com.pushlog.logreader;
import java.util.List;
import com.pushlog.entity.DownloadReport;
import com.pushlog.entity.UploadReport;;

public interface ReadSvclog {
	public List<String> ReadLog(String path);
	public List<DownloadReport> GetDownloadReportType(List<String> path);
	public List<UploadReport> GetUploadReportType(List<String> path);
}
