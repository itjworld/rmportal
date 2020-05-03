package com.rmportal.service;

import java.util.List;

import com.rmportal.vo.MailDetails;

public interface MailService {
	String sendEmail(MailDetails mail);

	String triggerEmail(MailDetails mail);
	
	String sendEmail(MailDetails mail,List<?> data,String headerName[],String fields [],final String fileName);
}
