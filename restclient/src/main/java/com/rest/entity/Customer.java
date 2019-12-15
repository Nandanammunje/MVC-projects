package com.rest.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="customer")
public class Customer  implements Serializable{
	
@Id	
@Column(name ="userId")	
private int userId;
@Column(name="id")
private int id;
@Column(name="title")
private String title;
@Column(name="body")
private String body;


@Override
public String toString() {
	return "Customer [userId=" + userId + ", id=" + id + ", title=" + title + ", body=" + body + "]";
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getBody() {
	return body;
}
public void setBody(String body) {
	this.body = body;
} 




}
