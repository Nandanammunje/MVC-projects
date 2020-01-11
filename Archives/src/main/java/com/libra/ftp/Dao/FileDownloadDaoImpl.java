package com.libra.ftp.Dao;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.libra.ftp.Constants.BackendConstants;

@Repository
public class FileDownloadDaoImpl implements FileDownloadDao {

	@Override
	public String[] GetDocList() {
		// TODO Auto-generated method stub
		HttpClient client=HttpClientBuilder.create().build();
		HttpPost post=new HttpPost(BackendConstants.LIBRAARCHIVELISTURL);
		String BookList[]=null;
		try {
			HttpResponse response=client.execute(post);
			HttpEntity entity=response.getEntity();
			String content=EntityUtils.toString(entity);
			ObjectMapper mapper=new ObjectMapper();
			 BookList=mapper.readValue(content,String[].class);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return BookList;
	}

}
