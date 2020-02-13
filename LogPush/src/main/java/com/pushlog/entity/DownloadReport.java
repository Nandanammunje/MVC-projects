package com.pushlog.entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DownloadReport {
    private String name;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date logtime;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getLogtime() {
		return logtime;
	}
	public void setLogtime(Date logtime) {
		this.logtime = logtime;
	}
	
	
}
