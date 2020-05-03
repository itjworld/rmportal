package com.rmportal.vo;

import lombok.Data;

@Data
public class GuestVM {

	private long id;
	private int srNo;
	private String name;
	private String email;
	private String checkindate;
	private int rent;
	private int security;
	private int elctricityPaid;
	private String mobile;
	private int roomNo;
}
