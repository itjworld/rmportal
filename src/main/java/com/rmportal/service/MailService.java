package com.rmportal.service;

public interface MailService {
	String sendEmail(String recepients, String subject, String cc, String message);

	String triggerEmail(String content, String receipients, String cc, String subject);
}
