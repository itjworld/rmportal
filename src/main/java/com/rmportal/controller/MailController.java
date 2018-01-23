package com.rmportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rmportal.util.MailService;
import com.rmportal.vo.MailDetails;

@Controller
@RequestMapping(value = "/api/v1")
public class MailController {

	@Autowired
	private MailService mailService;

	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
	public ResponseEntity<String> sendMail(@RequestBody MailDetails mail) {
		System.out.println(String.format("sendMail - >  to : %s :: cc : %s :: subject : %s :: message : %s",
				mail.getTo(), mail.getCc(), mail.getSubject(), mail.getMessage()));
		return new ResponseEntity<String>(
				mailService.triggerEmail(mail.getMessage(), mail.getTo(), mail.getCc(), mail.getSubject()),
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sendMailAsPdf", method = RequestMethod.POST)
	public ResponseEntity<String> sendMailWithPDF(@RequestBody MailDetails mail) {
		System.out.println(String.format("sendMailWithPDF - >  to : %s :: cc : %s :: subject : %s :: message : %s",
				mail.getTo(), mail.getCc(), mail.getSubject(), mail.getMessage()));
		return new ResponseEntity<String>(
				mailService.sendEmail(mail.getTo(), mail.getSubject(), mail.getCc(), mail.getMessage()), HttpStatus.OK);
	}
}
