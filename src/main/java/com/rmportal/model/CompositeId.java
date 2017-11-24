package com.rmportal.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class CompositeId implements Serializable {

	private static final long serialVersionUID = 5865035937378773109L;
	
//	@Column(name = "CODE")
	private String code;

//	@Column(name = "TYPE")
	private String type;

	public CompositeId() {
	}

	public CompositeId(String code, String type) {
		this.code = code;
		this.type = type;
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
