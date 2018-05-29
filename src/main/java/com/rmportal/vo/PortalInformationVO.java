package com.rmportal.vo;



public class PortalInformationVO {

	private long id;
	private int rent=0;
	private int deposit=0;
	private String street1;
	private String street2;
	private String location;
	private String content;
	private String gender;
	private int roomType;
	private int occupied;
	private String condition;
	private String roomNo;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getRent() {
		return rent;
	}
	public void setRent(int rent) {
		this.rent = rent;
	}
	public int getDeposit() {
		return deposit;
	}
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	public String getStreet1() {
		return street1;
	}
	public void setStreet1(String street1) {
		this.street1 = street1;
	}
	public String getStreet2() {
		return street2;
	}
	public void setStreet2(String street2) {
		this.street2 = street2;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getRoomType() {
		return roomType;
	}
	public void setRoomType(int roomType) {
		this.roomType = roomType;
	}
	public int getOccupied() {
		return occupied;
	}
	public void setOccupied(int occupied) {
		this.occupied = occupied;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	
}
