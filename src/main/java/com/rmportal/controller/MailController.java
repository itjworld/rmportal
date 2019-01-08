package com.rmportal.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rmportal.service.InfoService;
import com.rmportal.service.MailService;
import com.rmportal.vo.MailDetails;

@Controller
@RequestMapping(value = "/api/v1")
public class MailController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private InfoService infoService;

	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
	public ResponseEntity<String> sendMail(@RequestBody MailDetails mail) {
		System.out.println(String.format("sendMail - >  to : %s :: cc : %s :: subject : %s :: message : %s",
				mail.getTo(), mail.getCc(), mail.getSubject(), mail.getMessage()));
		return new ResponseEntity<String>(mailService.triggerEmail(mail), HttpStatus.OK);
	}

	@RequestMapping(value = "/sendMailAsPdf", method = RequestMethod.POST)
	public ResponseEntity<String> sendMailWithPDF(@RequestBody MailDetails mail) {
		System.out.println(String.format("sendMailWithPDF - >  to : %s :: cc : %s :: subject : %s :: message : %s",
				mail.getTo(), mail.getCc(), mail.getSubject(), mail.getMessage()));
		return new ResponseEntity<String>(send(mail), HttpStatus.OK);
	}

	private String send(MailDetails mail) {
		List<?> data=null;
		List<String> headerName= null;
		String fileName=null;
		List<String> fields =null;
		boolean isDefault=true;
		switch (mail.getType().toUpperCase()) {
		case "RR":
			data=infoService.getRecords();
			fileName="record.pdf";
			headerName=Arrays.asList("Sr No", "Room", "Name", "Mobile", "Email", "Rent", "Security", "Check_In_Date");
			fields=Arrays.asList( "srNo", "roomNo", "fName", "mobile", "email", "mapping.rent", "security", "checkindate");
			break;
		case "MR":
			data=infoService.getMyRecords(mail.getReference()).getData();
			fileName="myrecord.pdf";
			headerName=Arrays.asList("Id", "Rent", "Electricity Paid", "Electricity Bill", "Security", "Month");
			fields=Arrays.asList("id", "rent", "elecBillPaid", "electricBill", "security", "currentMonth");
			break;
		case "RD":
			data=infoService.getRentDetail(Long.parseLong(mail.getReference())).getData();
			fileName="rentdetail.pdf";
			headerName=Arrays.asList("Id", "Rent", "Electricity Paid", "Electricity Bill", "Security", "Month");
			fields=Arrays.asList( "id", "rent", "elecBillPaid", "electricBill", "security", "currentMonth");
			break;

		default:
			isDefault=true;
			LOGGER.error("Mail Type did not match");
			break;
			
		}
		if(!isDefault) {
			return "{\"message\": \"ERROR\"}" ;
		}
		return mailService.sendEmail(mail, data, headerName.toArray(new String[0]), fields.toArray(new String[0]),fileName);

	}
}
