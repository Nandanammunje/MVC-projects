package com.http.download.Dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.http.download.config.BackendConstants;

@Repository
public class HttpDownloadDaoImpl implements HttpDownloadDao  {

	@Override
	public void SaveDocument(String pth) {
		// TODO Auto-generated method stub
		HttpClient client=HttpClientBuilder.create().build();
		HttpPost post=new HttpPost(BackendConstants.DOCBASEURL+pth+BackendConstants.DOCEXT);
		             try {
		            	OutputStream outstream=new FileOutputStream(BackendConstants.DOCDESTPATH+pth+BackendConstants.DOCEXT); 
							
							
						HttpResponse response=client.execute(post);
						HttpEntity entity=response.getEntity();
						   	entity.writeTo(outstream);
						   	
						                  
						                  
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	}

	@Override
	public String[] GetDocList() {
		// TODO Auto-generated method stub

		HttpClient client=HttpClientBuilder.create().build();
		HttpPost post=new HttpPost(BackendConstants.DOCLISTURL);
		String Booklist[]=null;
		ObjectMapper mapper=new ObjectMapper();
		try
		{
		
			HttpResponse response=client.execute(post);
			HttpEntity entity=response.getEntity();
			String content=EntityUtils.toString(entity);
	
			 Booklist=mapper.readValue(content,String[].class);
		
			
			
			
		}
		catch(Exception e)
		{
			
		}
		return Booklist;
	}

}
