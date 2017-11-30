package com.rmportal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmportal.model.Enquiry;
import com.rmportal.repositories.EnquiryRepository;
import com.rmportal.service.EnquiryService;

@Service
public class EnquiryServiceImpl implements EnquiryService {

	@Autowired
	private EnquiryRepository enquiryRepository;

	@Override
	public Enquiry save(Enquiry enquiry) {
		enquiryRepository.save(enquiry);
		return enquiry;
	}

}
