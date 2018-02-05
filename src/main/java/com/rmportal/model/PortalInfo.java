package com.rmportal.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "RM_PORTAL_INFO", uniqueConstraints = @UniqueConstraint(columnNames = { "CODE", "TYPE" }))
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PortalInfo implements Serializable {

	private static final long serialVersionUID = -2591027128796926656L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "CODE")
	private String code;

	@Column(name = "TYPE")
	private String type;

	@Column(name = "STATUS", nullable = false)
	private boolean status;

	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "VALUE", nullable = false)
	private int value = 0;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof CompositeId))
			return false;
		CompositeId obj = (CompositeId) o;
		return Objects.equals(getType(), obj.getType()) && Objects.equals(getCode(), obj.getCode());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getType(), getCode());
	}
}
