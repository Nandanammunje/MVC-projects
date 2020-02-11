package com.pushlog.logreader;
import java.util.List;
import com.pushlog.entity.DownloadReport;;

public interface ReadSvclog {
	public List<String> ReadLog(String path);
	public List<DownloadReport> GetReportType(List<String> path);
}
