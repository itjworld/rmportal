package com.rmportal.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "RM_ROOM_BOOKED")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GuestDetail implements Serializable {

	private static final long serialVersionUID = -4512115118990500916L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	@Column(name = "FNAME")
	private String fName;

	@Column(name = "LNAME")
	private String lName;

	@Column(name = "EMAIL", unique = true)
	private String email;

	@Column(name = "MOBILE")
	private String mobile;

	@Column(name = "RELATIVE_MOBILE")
	private String relativeMobile;

	@Column(name = "RELATIVE_RELATION")
	private String relation;

	@Column(name = "ADDRESS")
	private String address;

	@Transient
	private int rent = 0;

	@Column(name = "DEPOSITE")
	private int security = 0;

	@Column(name = "Description")
	private String desc;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MAPPING_ID")
	private PortalMappingInfo mapping;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "guestDetail")
	private List<GuestPayment> paymentList;

	@Transient
	private Integer roomNo;

	@Transient
	private Long addressId;

	@Column(name = "ACTIVE")
	boolean active = true;

	@Column(name = "UPDATED_DATETIME")
	@UpdateTimestamp
	private Date updateDateTime;

	@Column(name = "CREATED_DATETIME")
	@CreationTimestamp
	private Date createDateTime;

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
		if (mapping != null)
			rent = mapping.getRent();
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
		if (mapping != null)
			roomNo = mapping.getRoomNumber();
		return roomNo;
	}

	public void setRoomNo(Integer roomNo) {
		this.roomNo = roomNo;
	}

	public Long getAddressId() {
		if (mapping != null)
			addressId = mapping.getAddress().getId();
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public List<GuestPayment> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List<GuestPayment> paymentList) {
		this.paymentList = paymentList;
	}

}
