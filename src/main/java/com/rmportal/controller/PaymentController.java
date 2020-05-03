package com.rmportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rmportal.service.PaymentService;

@RestController
@RequestMapping(value = "/api/v1")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@GetMapping("/month/records/insert")
	public ResponseEntity<Void> insertRentRecords() {
		boolean result = paymentService.insertMonthRecords();
		if (result)
			return new ResponseEntity<Void>(HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
