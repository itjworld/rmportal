package com.rmportal.enums;

public enum DomainType {
	GENDER, LOCATION, AC, ROOM;
	public static DomainType getType(String type) {
		for (DomainType domain : values()) {
			if (domain.name().equalsIgnoreCase(type))
				return domain;
		}
		return null;
	}
}
