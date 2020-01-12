package com.libra.ftp.Dao;

import java.awt.CardLayout;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.libra.ftp.Constants.BackendConstants;

@Repository
public class FileDownloadDaoImpl implements FileDownloadDao {

	 Logger logger = Logger.getLogger(FileDownloadDaoImpl.class);
	@Override
	public String[] GetDocList() {
		// TODO Auto-generated method stub
		HttpClient client=HttpClientBuilder.create().build();
		HttpPost post=new HttpPost(BackendConstants.LIBRAARCHIVELISTURL);
		String BookList[]=null;
		JCS cache;
		try {
			cache=JCS.getInstance("library");
		    BookList=(String[])cache.get("Books");
		    if(BookList!=null && BookList.length!=0)
		    {
		    logger.debug("extracted from the cache"+BookList.length);
		    logger.debug("cache stats"+cache.getStats());
		    System.out.println("extracted from cache");
		    }
		    else
		    {
		    	logger.info("cache status before addition"+cache.getStats());
			HttpResponse response=client.execute(post);
			HttpEntity entity=response.getEntity();
			String content=EntityUtils.toString(entity);
			ObjectMapper mapper=new ObjectMapper();
			 BookList=mapper.readValue(content,String[].class);
			 cache.put("Books",BookList);
			 logger.info("cache status after addition "+cache.getStats());
			 
		    }
		} catch (IOException | CacheException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Exception occured"+e);
		}
		return BookList;
	}
	@Override
	public String[] GetCacheData() {
		// TODO Auto-generated method stub
		JCS cache;
		String CachedBookList[]=null;
	  try
	  {
		  cache=JCS.getInstance("library");
		  CachedBookList=(String [])cache.get("Books");
		  if(CachedBookList!=null && CachedBookList.length > 0 )
		  {
			  logger.debug("properties exist in the cache "+cache.getStats());
		  }
		  else
		  {
			  String s[]=new String[1];
			  s[0]="Cache is Cleared";
			  
			  logger.info("cache is empty"+cache.getStats());
			  return s;
		  }
	  }
	  catch(CacheException e)
	  {
		  logger.error("encountered cache exception"+e);
	  }
	    
	  return CachedBookList;
	}

}
