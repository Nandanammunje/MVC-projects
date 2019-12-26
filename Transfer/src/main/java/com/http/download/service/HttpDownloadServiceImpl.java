package com.http.download.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;

@Service
public class HttpDownloadServiceImpl implements HttpDownloadService {

	@Override
	public void SaveDocument() {
		// TODO Auto-generated method stub
		HttpClient client=HttpClientBuilder.create().build();
		HttpPost post=new HttpPost("http://192.168.1.100:9097/documents/gate/EC_2003.pdf");
		             try {
		            	OutputStream outstream=new FileOutputStream("C:/Users/nandannayak/Videos/Captures/EC_2003.pdf"); 
							
							
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

}
