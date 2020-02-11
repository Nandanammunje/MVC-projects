package com.pushlog.logreader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.pushlog.constants.BackendConstants;
import com.pushlog.entity.DownloadReport;

public class ReadSvcLogimpl implements ReadSvclog {

	public List<String> ReadLog(String path) {
		// TODO Auto-generated method stub
		List<String>  collect=new ArrayList<String>();
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String date;
		if(cal.get(Calendar.MONTH)+1 >9)
		{
		 date=cal.get(Calendar.DATE)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR);
		}
		else
		{
		  date=cal.get(Calendar.DATE)+"-"+"0"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR);	
		}
		System.out.println(date);
		
		String filename = null;
		filename = path+date+BackendConstants.WEBSVCLOGEXT;
		
		String content;
        File weblog=new File(filename);		
        try {
			BufferedReader br=new BufferedReader(new FileReader(weblog));
			while((content=br.readLine())!=null)
			{
				System.out.println(content);
				collect.add(content);
				
			}
			br.close();
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return collect;
	}

	@Override
	public List<DownloadReport> GetReportType(List<String> path) {
		// TODO Auto-generated method stub
		List<DownloadReport> report;
		for(String s:path)
		{
			String arr[]=s.split("}");
			System.out.println(s);
		}
		return null;
	}

}
