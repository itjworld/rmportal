package com.rmportal.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "RM_GUEST_PAYMENT")
public class GuestPayment implements Serializable {

	private static final long serialVersionUID = -4512115118990500916L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	@Column(name = "RENT", nullable = false)
	private int rent = 0;

	@Column(name = "SECURITY", nullable = false)
	private int security = 0;

	@Column(name = "REMARKS")
	private String remarks;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ELECTRICITY_DETAIL_ID")
	private ElectricityDetail electricityDetail;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "GUEST_DETAIL_ID")
	private GuestDetail guestDetail;

	@Column(name = "ELECTRICITY_PAID")
	private Integer elecBillPaid;

	@Column(name = "UPDATED_DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updateDateTime;

	@Column(name = "CREATED_DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createDateTime;

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

	public int getSecurity() {
		return security;
	}

	public void setSecurity(int security) {
		this.security = security;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public GuestDetail getGuestDetail() {
		return guestDetail;
	}

	public void setGuestDetail(GuestDetail guestDetail) {
		this.guestDetail = guestDetail;
	}

	public Integer getElecBillPaid() {
		return elecBillPaid;
	}

	public void setElecBillPaid(Integer elecBillPaid) {
		this.elecBillPaid = elecBillPaid;
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

}
