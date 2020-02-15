package com.pushlog.starter;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pushlog.Dao.PushReportDao;
import com.pushlog.constants.BackendConstants;
import com.pushlog.entity.DownloadReport;
import com.pushlog.logreader.ReadSvclog;

public class Starter {
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("Application-Context.xml");
			ReadSvclog read=context.getBean("readlog",ReadSvclog.class);
	        List<String> content=read.ReadLog(BackendConstants.WEBSVCLOGPATH);
	        List<DownloadReport> report=read.GetReportType(content);
	         PushReportDao dao=context.getBean("pushdata",PushReportDao.class);
	                       
	                         for(DownloadReport rep:report)
	                         {
	                        	 dao.save(rep);
	                         }
	                                   
	         }

}
