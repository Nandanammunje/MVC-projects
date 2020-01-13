package com.nuance.starter;

import java.io.IOException;
import java.util.List;

import com.nuance.FileCopier.FileCopier;
import com.nuance.FileCopier.PathGenerator;

public class Starter {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		List<String> genpath;
PathGenerator gen=new PathGenerator();
genpath=gen.PathGen("C:\\Users\\nandannayak\\Documents\\Gate-ECE");
FileCopier copy=new FileCopier();

copy.FileCopy(genpath);
	}

}
