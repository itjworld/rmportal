package com.rmportal.vo;

import java.io.Serializable;

public class MailDetails implements Serializable {
	private static final long serialVersionUID = -3055747144786675759L;
	private String to;
	private String cc;
	private String subject;
	private String message;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
