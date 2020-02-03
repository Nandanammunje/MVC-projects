package com.pushlog.logreader;
import java.util.List;
import com.pushlog.entity.DownloadReport;;

public interface ReadSvclog {
	public List<DownloadReport> ReadLog(String path);
}
