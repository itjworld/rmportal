package com.rmportal.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rmportal.model.User;

@JsonInclude(Include.NON_NULL)
public class UserDTO {

	private String username;
	private String email;
	private boolean status;
	private String fName;
	private String lName;
	private long id;

	public UserDTO(User user) {
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.status = user.isStatus();
		this.fName = user.getfName();
		this.lName = user.getlName();
		this.id = user.getId();
	}
	
	public UserDTO(){
		
	}

	public static UserDTO of(User user) {
		return new UserDTO(user);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
