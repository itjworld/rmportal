package com.rmportal.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RM_PORTAL_MAPPING_INFO")
public class PortalMappingInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1764428332848735644L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	
	@OneToOne
	@JoinColumn(name="GENDER_TYPE",nullable=false)
	private PortalInfo gender;
	
	@OneToOne
	@JoinColumn(name="AIR_COND_TYPE",nullable=false)
	private PortalInfo condition;
	
	@OneToOne
	@JoinColumn(name="ROOM_TYPE",nullable=false)
	private PortalInfo roomType;
	
	@Column(name="RENT",nullable=false)
	private int rent=0;
	
	@Column(name="DEPOSIT",nullable=false)
	private int deposit=0;
	
	@Column(name="OCCUPIED",nullable=false)
	private int occupied = 0;
	
	@OneToOne
	@JoinColumn(name="ADDRESS_ID",nullable=false)
	private AddressInfo address;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PortalInfo getGender() {
		return gender;
	}

	public void setGender(PortalInfo gender) {
		this.gender = gender;
	}

	public PortalInfo getCondition() {
		return condition;
	}

	public void setCondition(PortalInfo condition) {
		this.condition = condition;
	}

	public PortalInfo getRoomType() {
		return roomType;
	}

	public void setRoomType(PortalInfo roomType) {
		this.roomType = roomType;
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

	public AddressInfo getAddress() {
		return address;
	}

	public void setAddress(AddressInfo address) {
		this.address = address;
	}
	
	
	
	

}
