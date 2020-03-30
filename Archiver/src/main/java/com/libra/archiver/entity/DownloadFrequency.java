package com.libra.archiver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author nandannayak
 *
 */
@Entity
@Table(name = "downloadfrequency")
public class DownloadFrequency {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name ="id")
	private int id;
     @Column(name = "name")
	private String name;
     @Column(name = "frequency")
	private int frequency;
     
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
     
     
     
     
     
     
}
