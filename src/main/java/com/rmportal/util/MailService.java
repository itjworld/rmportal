package com.rmportal.util;

public interface MailService {
	void sendEmail(String recepients, String subject, String cc, String message);

	public String triggerEmail(String content, String receipients, String cc, String subject);
}
