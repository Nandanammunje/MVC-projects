package com.libra.archiver.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libra.archiver.Dao.ArchiverDao;
import com.libra.archiver.entity.DownloadFrequency;

@Service
public class ArchiverServiceImpl implements ArchiverService {

	@Autowired
	private ArchiverDao dao;
	
	@Transactional
	@Override
	public List<DownloadFrequency> GetData() {
		// TODO Auto-generated method stub
		
		return dao.GetData();
	}

}
