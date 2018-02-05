package com.rmportal.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author Ashish Jaiswal
 *
 */
@Entity
@Table(name = "RM_ADDRESS_INFO")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable(value = true)
public class AddressInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6477184781780572554L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	
	@Column(name = "STREET1",nullable=false)
	private String street1;
	
	@Column(name = "STREET2",nullable=true)
	private String street2;
	
	@OneToOne
	@JoinColumn(name="LOCATION",nullable=false)
	private PortalInfo location;
	
	@Column(name = "CONTACT",nullable=false)
	private String contact;
	
	@Column(name = "CONTENT",nullable=true)
	private String content;
	
	@Column(name = "NAME",nullable=true)
	private String name;
	
	@Column(name = "EMAIL",nullable=true)
	private String email;
	
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

	

	public PortalInfo getLocation() {
		return location;
	}

	public void setLocation(PortalInfo location) {
		this.location = location;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
