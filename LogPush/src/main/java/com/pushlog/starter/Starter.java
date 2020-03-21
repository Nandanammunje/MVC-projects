package com.pushlog.starter;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pushlog.Dao.PushReportDao;
import com.pushlog.constants.BackendConstants;
import com.pushlog.entity.DownloadReport;
import com.pushlog.entity.UploadReport;
import com.pushlog.logreader.ReadSvclog;

public class Starter {
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("Application-Context.xml");
			ReadSvclog read=context.getBean("readlog",ReadSvclog.class);
	        List<String> downloadcontent=read.ReadLog(BackendConstants.WEBSVCLOGPATH);
	        List<String> uploadcontent=read.ReadLog(BackendConstants.WEBSVCUPLOGPATH);
	        PushReportDao dao=context.getBean("pushdata",PushReportDao.class);
	        if(downloadcontent!=null)
	        {
	        	 List<DownloadReport> Downloadreport=read.GetDownloadReportType(downloadcontent);
	        	  for(DownloadReport rep:Downloadreport)
	                {
	                     dao.save(rep,"downloadreport");
	                }
	        }
	        if(uploadcontent!=null)
	        {
	        	List<UploadReport> UploadReport=read.GetUploadReportType(uploadcontent);

                for(UploadReport rep:UploadReport)
                {
              	  dao.save(rep,"uploadreport");
                }
             
	        }
	       
	       context.close();
	                                   
	         }

}
