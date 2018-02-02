package com.rmportal.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "RM_GUEST_PAYMENT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GuestPayment implements Serializable {

	private static final long serialVersionUID = -4512115118990500916L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	@Column(name = "RENT", nullable = false)
	private int rent = 0;

	@Column(name = "REMARKS")
	private String remarks;

	@Transient
	private int security;

	@Transient
	private long electricBill;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ELECTRICITY_DETAIL_ID")
	private ElectricityDetail electricityDetail;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "GUEST_DETAIL_ID")
	private GuestDetail guestDetail;

	@Column(name = "ELECTRICITY_PAID")
	private Integer elecBillPaid = 0;

	@Column(name = "UPDATED_DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updateDateTime;

	@Column(name = "CREATED_DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createDateTime;
	
	@Transient
	private String currentMonth;

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

	public ElectricityDetail getElectricityDetail() {
		return electricityDetail;
	}

	public void setElectricityDetail(ElectricityDetail electricityDetail) {
		this.electricityDetail = electricityDetail;
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
		setCurrentMonth(createDateTime.toString());
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public int getSecurity() {
		if (guestDetail != null) {
			if (electricityDetail != null) {
				getElectricBill();
			}
			return guestDetail.getSecurity();
		}
		return security;
	}

	public void setSecurity(int security) {
//		if (guestDetail != null)
//			this.security = guestDetail.getSecurity();
//		else
			this.security = security;
	}

	public long getElectricBill() {
		if (electricityDetail != null) {
			long total = (electricityDetail.getCurrentReading() - electricityDetail.getLastReading())
					* electricityDetail.getUnitRate();
			int guests = guestDetail.getMapping().getOccupied();
			if (guests != 0)
				this.electricBill = total / guests;
		}
		return electricBill;
	}

	public void setElectricBill(long electricBill) {
		this.electricBill = electricBill;
	}

	public String getCurrentMonth() {
		return currentMonth;
	}

	public void setCurrentMonth(String currentMonth) {
		this.currentMonth = currentMonth;
	}
	
}
