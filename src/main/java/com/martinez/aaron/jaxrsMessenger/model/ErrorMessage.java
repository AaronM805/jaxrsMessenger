package com.martinez.aaron.jaxrsMessenger.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement // To convert to JSON
public class ErrorMessage {
	private String errorMessage;
	private int errorCode;
	
	// This is needed so object can be serialized/desirialized.
	public ErrorMessage() {
		
	}
	
	public ErrorMessage(String errorMessage, int errorCode, String documentation) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.documentation = documentation;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getDocumentation() {
		return documentation;
	}
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
	private String documentation;
}
