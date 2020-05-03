package com.rmportal.enums;

public enum Credential {
	BAD("Invalid login/password"), LOCKED, DISABLED, EXPIRED, CREDENTIAL_EXPIRED, INACTIVE("User profile is not active");
	String message;

	Credential(String message) {
		this.message = message;
	}

	Credential() {

	}

	public String val() {
		return message;
	}
}
