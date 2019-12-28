package com.restdata.dao;
import java.io.File;

import org.springframework.stereotype.Repository;

@Repository
class FilecontentDaoImpl implements FilecontentDao {

	@Override
	public String[] filecontent() {
		// TODO Auto-generated method stub
		
		File folder=new File("C:/Users/nandannayak/Documents/Gate-ECE");
		 String filelist[]=folder.list();
		  return filelist;
	}

	

}
