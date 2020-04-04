package com.restdata.dao;

import java.util.Comparator;

import com.restdata.entity.DownloadFrequency;

public class SortByFreq implements Comparator<DownloadFrequency> {

	@Override
	public int compare(DownloadFrequency o1, DownloadFrequency o2) {
		// TODO Auto-generated method stub
		return o2.getFrequency()-o1.getFrequency();
	}

}
