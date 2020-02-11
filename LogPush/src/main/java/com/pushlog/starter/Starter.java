package com.pushlog.starter;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pushlog.constants.BackendConstants;
import com.pushlog.logreader.ReadSvclog;

public class Starter {
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("Application-Context.xml");
			ReadSvclog read=context.getBean("readlog",ReadSvclog.class);
	               List<String> content=read.ReadLog(BackendConstants.WEBSVCLOGPATH);
	                                      //System.out.println(read.GetReportType(content));
	
	                                          
	
	
	
	
	
	
	
	
	
	
	}

}
