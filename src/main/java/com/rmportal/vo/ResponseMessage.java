package com.rmportal.vo;

public class ResponseMessage {

	private String message;
	private boolean status;
	private int code;

	public ResponseMessage() {
	}

	public ResponseMessage(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public ResponseMessage(String message, boolean status) {
		this.status = status;
		this.message = message;
	}
	
	public ResponseMessage(int code, String message, boolean status) {
		this.code = code;
		this.message = message;
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
