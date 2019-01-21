package com.rmportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rmportal.service.PaymentService;

@RestController("/api/v1")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;

	@PostMapping("/insert/records")
	public ResponseEntity<String> insertRentRecords() {
		boolean result = paymentService.insertMonthRecords();
		return new ResponseEntity<String>("Records inserted successfully", HttpStatus.OK);

	}

}
