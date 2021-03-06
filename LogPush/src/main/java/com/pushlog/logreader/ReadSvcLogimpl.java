package com.pushlog.logreader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pushlog.constants.BackendConstants;
import com.pushlog.entity.DownloadReport;
import com.pushlog.entity.UploadReport;

public class ReadSvcLogimpl implements ReadSvclog {

	public List<String> ReadLog(String path) {
		// TODO Auto-generated method stub
		List<String>  collect=new ArrayList<String>();
		LocalDate time=LocalDate.now();
		time=time.minusDays(1);
		DateTimeFormatter format=DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String date=time.format(format);
	    System.out.println(date);
		
		String filename = null;
		filename = path+date+BackendConstants.WEBSVCLOGEXT;
		
		String content;
        File weblog=new File(filename);		
        try {
			BufferedReader br=new BufferedReader(new FileReader(weblog));
			while((content=br.readLine())!=null)
			{
		
				collect.add(content);
				
			}
			System.out.println(collect);
			br.close();
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			
			return null;
		}
		return collect;
	}

	@Override
	public List<DownloadReport> GetDownloadReportType(List<String> path) {
		// TODO Auto-generated method stub
		List<DownloadReport> report=new ArrayList<DownloadReport>();
		int begin=0;
		
		ObjectMapper map=new ObjectMapper();
		for(String s:path)
		{
			for(int i=0;i<s.length();i++)
			{
				if(s.charAt(i)=='{')
				{
					begin=i;
				}
				if(s.charAt(i)=='}')
				{
					
					try {
						
						report.add(map.readValue(s.substring(begin,i+1),DownloadReport.class));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
		}
		
	}
		return report;
	}

	@Override
	public List<UploadReport> GetUploadReportType(List<String> path) {
		// TODO Auto-generated method stub
		List<UploadReport> report=new ArrayList<UploadReport>();
		int begin=0;
		ObjectMapper map=new ObjectMapper();
		for(String s:path)
		{
			for(int i=0;i<s.length();i++)
			{
				if(s.charAt(i)=='{')
				{
					begin=i;
				}
				if(s.charAt(i)=='}')
				{
					
					try {
						
						report.add(map.readValue(s.substring(begin,i+1),UploadReport.class));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						
						e.printStackTrace();
						
					}
				
				}
		}
		
	}
		return report;
	}
}

