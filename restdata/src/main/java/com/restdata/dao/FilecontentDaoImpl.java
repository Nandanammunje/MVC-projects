package com.restdata.dao;
import java.io.File;

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

	

}
