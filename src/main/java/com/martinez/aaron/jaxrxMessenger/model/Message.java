package com.martinez.aaron.jaxrxMessenger.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
 
// This annotation is needed when Jersey tries to convert a Message to XML
@XmlRootElement
public class Message {
	
	private long id;
	private String message;
	private Date created;
	private String author;

	
	/**
	 * Default constructor. Make sure you always have this. THis is used for XML and JSON conversion.
	 */
	public Message() {
		
	}
	public Message(long id, String message, String author) {
		super();
		this.id = id;
		this.message = message;
		this.author = author;
		created = new Date();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

}
