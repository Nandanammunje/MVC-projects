package com.nuance.FileCopier;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import com.nuance.constants.BackendConstants;

public class FileCopier {


	public void FileCopy(List<String> pathlist) throws IOException
	{
		
		
		File file =new File(BackendConstants.FILESOURCE);
		if(!file.exists())
		{
			file.mkdir();
		}
		for(String s:pathlist)
		{
			File src=new File(s);
			int index=s.lastIndexOf("\\");
           String destpath=BackendConstants.FILESOURCE+"/"+s.substring(index+1,s.length());
           File dest=new File(destpath);
           Files.copy(src.toPath(),dest.toPath());
              
		}
		
	}
	
}
