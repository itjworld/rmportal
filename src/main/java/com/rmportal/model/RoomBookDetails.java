package com.rmportal.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "RM_ROOM_BOOKED")
public class RoomBookDetails implements Serializable{
	
	private static final long serialVersionUID = -4512115118990500916L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	
	@JoinColumn(name="FNAME")
	private String fName;
	
	@JoinColumn(name="LNAME")
	private String lName;
	
	@JoinColumn(name="EMAIL")
	private String email;
	
	@JoinColumn(name="MOBILE")
	private String mobile;
	
	@JoinColumn(name="RELATIVE_MOBILE")
	private String relativeMobile;
	
	@JoinColumn(name="RELATIVE_RELATION")
	private String relation;
	
	@JoinColumn(name="ADDRESS")
	private String address;
	
	@Column(name="RENT",nullable=false)
	private int rent=0;
	
	@Column(name="SECURITY",nullable=false)
	private int security=0;
	
	@Column(name="Description")
	private String desc;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MAPPING_ID")
	private PortalMappingInfo mapping;
	
	@Transient
	private Integer roomNo;
	
	@Transient
	private Long addressId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRelativeMobile() {
		return relativeMobile;
	}

	public void setRelativeMobile(String relativeMobile) {
		this.relativeMobile = relativeMobile;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getRent() {
		return rent;
	}

	public void setRent(int rent) {
		this.rent = rent;
	}

	public int getSecurity() {
		return security;
	}

	public void setSecurity(int security) {
		this.security = security;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public PortalMappingInfo getMapping() {
		return mapping;
	}

	public void setMapping(PortalMappingInfo mapping) {
		this.mapping = mapping;
	}

	public Integer getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(Integer roomNo) {
		this.roomNo = roomNo;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

}
