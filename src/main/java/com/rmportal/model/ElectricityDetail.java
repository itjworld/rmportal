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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "RM_ELECTRICITY")
public class ElectricityDetail implements Serializable{
	
	private static final long serialVersionUID = -4512115118990500916L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	
	@Column(name = "LAST_READING")
	private Long lastReading;
	
	@Column(name = "CURRENT_READING")
	private Long currentReading;
	
	@Column(name = "UNIT_RATE")
	private int unitRate;
	
	@Column(name = "UPDATED_DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updateDateTime;

	@Column(name = "CREATED_DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createDateTime;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MAPPING_ID")
	private PortalMappingInfo mapping;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getLastReading() {
		return lastReading;
	}

	public void setLastReading(Long lastReading) {
		this.lastReading = lastReading;
	}

	public Long getCurrentReading() {
		return currentReading;
	}

	public void setCurrentReading(Long currentReading) {
		this.currentReading = currentReading;
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

	public PortalMappingInfo getMapping() {
		return mapping;
	}

	public void setMapping(PortalMappingInfo mapping) {
		this.mapping = mapping;
	}

	public int getUnitRate() {
		return unitRate;
	}

	public void setUnitRate(int unitRate) {
		this.unitRate = unitRate;
	}
	
}
