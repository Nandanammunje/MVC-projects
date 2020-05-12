package com.restdata.dao;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.jcs.JCS;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restdata.config.BackendConstants;
import com.restdata.entity.DownloadFrequency;

@Repository
class FilecontentDaoImpl implements FilecontentDao {

	@Override
	public String[] filecontent() {
		// TODO Auto-generated method stub

		File folder = new File(BackendConstants.DOCPATH);
		String filelist[] = folder.list();
		JCS cache;
		String statcontent=null;
		List<DownloadFrequency> frequency;

		try {
			cache = JCS.getInstance("book");
			if (cache.get("downloadstat") != null) {
				filelist = (String[]) cache.get("downloadstat");

			} else {
				WebTarget target = ClientBuilder.newClient().target(BackendConstants.STATPROVIDERURL);
				try {

					Response resp = target.request().async().post(Entity.text(" ")).get();
					statcontent = resp.readEntity(String.class);

				} catch (Exception e) {

					statcontent = GetSyncLibraBookList();
				}
				
				ObjectMapper map = new ObjectMapper();
				frequency = Arrays.asList(map.readValue(statcontent, DownloadFrequency[].class));
				filelist = OrderListByFrequency(frequency, filelist);
				cache.put("downloadstat", filelist);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filelist;
	}

	@Override
	public String filelastmodified() {
		// TODO Auto-generated method stub
		File file = new File(BackendConstants.DOCPATH);
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		return sdf.format(file.lastModified()).toString();
	}

	public String[] OrderListByFrequency(List<DownloadFrequency> stat, String filelist[]) {
		Collections.sort(stat, new SortByFreq());
		HashMap<String, Integer> map = new HashMap<>();
		List<String> sortedlist = new ArrayList<String>();

		for (String name : filelist)
			map.put(name, 0);

		for (DownloadFrequency freq : stat) {
			if (map.containsKey(freq.getName())) {
				sortedlist.add(freq.getName());
				map.remove(freq.getName());
			}
		}
		for (String key : map.keySet()) {
			sortedlist.add(key);
			
		}

		filelist = sortedlist.toArray(new String[0]);
		
		return filelist;

	}

	@Override
	public void CacheClear() {
		// TODO Auto-generated method stub
		JCS cache;
		try {
			cache = JCS.getInstance("book");
			cache.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String GetSyncLibraBookList() {
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(BackendConstants.STATPROVIDERURL);
		HttpResponse response = null;
		String statcontent = null;
		try {
			response = client.execute(post);
			HttpEntity entity = response.getEntity();
			statcontent = EntityUtils.toString(entity);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return statcontent;
	}

}
