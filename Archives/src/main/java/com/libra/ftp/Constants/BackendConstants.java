package com.libra.ftp.Constants;

public class BackendConstants {

	
	public static final String LIBRAARCHIVELISTURL="http://localhost:9097/restdata/getdata/getfilename";
	public static final String DISPATCHERSUFFIX=".jsp";
	public static final String  DISPATCHERPREFIX="/WEB-INF/view/";
	public static final String DOCURL="http://localhost:9097/documents/gate/";
	public static final String DOWNLOADDOCLOCATION="C:/docdownloads/";
	public static final String FILEEXTENSION=".pdf";
	public static final String FILEMODIFIEDTIME="http://localhost:9097/restdata/getdata/getlastmodifiedtime";
	public static final String LIBRADBPUSHURL="http://localhost:9097/LibraDBPush/savereport";
	public static final String DATEPATTERN="dd/MM/yyyy";
	public static final String WEBSVCLOGPATTERN="dd-MM-yyyy";
	public static final String WEBSVCLOGLOCATION="C:/WebsvcLogs/Websvclog";
	public static final String LOGEXT=".log";
	public static final String LIBRAUPLOADREPORT="http://localhost:9097/LibraDBPush/saverecord";
	public static final String WEBSVCLOGUPLOAD="C:/WebsvcLogs/WebsvcUplog";
	public static final String LIBRAMQURL="http://localhost:8080/queue/push";
}
