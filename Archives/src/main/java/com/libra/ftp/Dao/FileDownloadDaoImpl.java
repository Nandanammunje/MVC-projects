package com.libra.ftp.Dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.sound.sampled.LineEvent.Type;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.libra.ftp.Constants.BackendConstants;
import com.libra.ftp.Entity.DownloadReport;
import com.libra.ftp.Entity.UploadReport;

@Repository
public class FileDownloadDaoImpl implements FileDownloadDao {

	Logger logger = Logger.getLogger(FileDownloadDaoImpl.class);

	@Override
	public String[] GetDocList() {
		// TODO Auto-generated method stub
		JCS cache;
		String BookList[] = null;
		String Content = null;
		try {
			cache = JCS.getInstance("library");
			BookList = (String[]) cache.get("Books");
			if (BookList != null && BookList.length != 0) {
				logger.debug("extracted from the cache" + BookList.length);
				logger.debug("cache stats" + cache.getStats());
				System.out.println("extracted from cache");
			} else {
				logger.info("cache status before addition" + cache.getStats());

				WebTarget target = ClientBuilder.newClient().target(BackendConstants.LIBRAARCHIVELISTURL);
				try {

					Response resp = target.request().async().post(Entity.text(" ")).get();
					Content = resp.readEntity(String.class);
					
				} catch (Exception e) {
					
					Content = GetSyncLibraBookList();
				}

				ObjectMapper mapper = new ObjectMapper();
				BookList = mapper.readValue(Content, String[].class);
				cache.put("Books", BookList);
				logger.info("cache status after addition " + cache.getStats());

			}
		} catch (IOException | CacheException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Exception occured" + e);
		}

		return BookList;
	}

	@Override
	public String[] GetCacheData() {
		// TODO Auto-generated method stub
		JCS cache;
		String CachedBookList[] = null;
		try {
			cache = JCS.getInstance("library");
			CachedBookList = (String[]) cache.get("Books");
			if (CachedBookList != null && CachedBookList.length > 0) {
				logger.debug("properties exist in the cache " + cache.getStats());
			} else {
				String s[] = new String[1];
				s[0] = "Cache is Cleared";

				logger.info("cache is empty" + cache.getStats());
				return s;
			}
		} catch (CacheException e) {
			logger.error("encountered cache exception" + e);
		}

		return CachedBookList;
	}

	@Override
	public void CacheClear() {
		// TODO Auto-generated method stub
		JCS cache;
		try {
			cache = JCS.getInstance("library");
			cache.clear();
			logger.info("Cache cleared");
		}

		catch (CacheException e) {
			logger.error("encountered cache exception" + e);
		}
	}

	@Override
	public void GetDoc(String filename) {
		// TODO Auto-generated method stub
		String url = BackendConstants.DOCURL + filename;
		String downloadpath = BackendConstants.DOWNLOADDOCLOCATION + filename;
		HttpClient client = HttpClientBuilder.create().build();

		HttpPost post = new HttpPost(url);
		OutputStream outstream = null;
		try {
			outstream = new FileOutputStream(downloadpath);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			logger.error("failed to save the  document " + e1);
		}
		try {
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			entity.writeTo(outstream);
			PushLibraArchiva(filename);
			outstream.close();

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			logger.error("failed to download document " + e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("error while saving the document " + e);
		}

	}

	public void PushLibraArchiva(String bookname) {
		SimpleDateFormat dateformat = new SimpleDateFormat(BackendConstants.DATEPATTERN);

		WebTarget targetMQ = ClientBuilder.newClient().target(BackendConstants.LIBRAMQURL);

		DownloadReport report = new DownloadReport();
		report.setName(bookname);
		report.setLogtime(dateformat.format(new Date()));
		try {

			ObjectMapper map = new ObjectMapper();
			String json = map.writeValueAsString(report);
			Response response = null;
			try {
				response = targetMQ.request().async().post(Entity.entity(json, MediaType.APPLICATION_JSON)).get();
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				PushLibraDownloadReport(json);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("failed to push data" + e);
		}
	}

	public void PushLibraDownloadReport(String jsondata) {

		SimpleDateFormat logdate = new SimpleDateFormat(BackendConstants.WEBSVCLOGPATTERN);
		String path = BackendConstants.WEBSVCLOGLOCATION + "-" + logdate.format(new Date()) + BackendConstants.LOGEXT;
		File file = new File(path);
		FileWriter fr = null;
		BufferedWriter br;
		Future<Response> response;
		WebTarget DBSvc = ClientBuilder.newClient().target(BackendConstants.LIBRADBPUSHURL);
		response = DBSvc.request().async().post(Entity.entity(jsondata, MediaType.APPLICATION_JSON));
		if (response == null) {
			try {
				fr = new FileWriter(file, true);
				br = new BufferedWriter(fr);
				br.write(jsondata + "|");
				br.close();
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void PushLibraUpFile(String filename) {
		SimpleDateFormat dateformat = new SimpleDateFormat(BackendConstants.DATEPATTERN);

		WebTarget targetMQ = ClientBuilder.newClient().target(BackendConstants.LIBRAMQUPLOADURL);

		UploadReport report = new UploadReport();
		report.setName(filename);
		report.setLogtime(dateformat.format(new Date()));

		try {

			ObjectMapper map = new ObjectMapper();
			String json = map.writeValueAsString(report);
			Response response = null;
			try {
				response = targetMQ.request().async().post(Entity.entity(json, MediaType.APPLICATION_JSON)).get();
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				PushLibraUploadReport(json);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("failed to push data" + e);
		}
	}

	public void PushLibraUploadReport(String jsondata) {
		SimpleDateFormat logdate = new SimpleDateFormat(BackendConstants.WEBSVCLOGPATTERN);
		String path = BackendConstants.WEBSVCLOGUPLOAD + "-" + logdate.format(new Date()) + BackendConstants.LOGEXT;
		File file = new File(path);
		FileWriter fr = null;
		BufferedWriter br;
		Future<Response> response;
		WebTarget DBSvc = ClientBuilder.newClient().target(BackendConstants.LIBRAUPLOADREPORT);
		response = DBSvc.request().async().post(Entity.entity(jsondata, MediaType.APPLICATION_JSON));
		if (response == null) {
			try {
				fr = new FileWriter(file, true);
				br = new BufferedWriter(fr);
				br.write(jsondata + "|");
				br.close();
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public String GetSyncLibraBookList() {
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(BackendConstants.LIBRAARCHIVELISTURL);
		String content = null;
		try {

			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			content = EntityUtils.toString(entity);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Exception occured" + e);
		}
		return content;
	}

}
