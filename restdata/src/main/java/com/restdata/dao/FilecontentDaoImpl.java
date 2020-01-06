package com.restdata.dao;
import java.io.File;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Repository;

import com.restdata.config.BackendConstants;

@Repository
class FilecontentDaoImpl implements FilecontentDao {

	@Override
	public String[] filecontent() {
		// TODO Auto-generated method stub
		
		File folder=new File(BackendConstants.DOCPATH);
		 String filelist[]=folder.list();
		  return filelist;
	}

	@Override
	public String filelastmodified() {
		// TODO Auto-generated method stub
	   File file=new File(BackendConstants.DOCPATH);
	   SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	   return sdf.format(file.lastModified()).toString();
	}

	

}
