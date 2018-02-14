package com.rmportal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rmportal.model.Enquiry;
import com.rmportal.repositories.EnquiryRepository;
import com.rmportal.service.EnquiryService;
import com.rmportal.service.MailService;
import com.rmportal.vo.EnquiryVO;
import com.rmportal.vo.MailDetails;

@Service
public class EnquiryServiceImpl implements EnquiryService {

	@Autowired
	private EnquiryRepository enquiryRepository;

	@Autowired
	private MailService mailService;

	@Value("${spring.mail.receipients}")
	private String receipients;

	@Value("${spring.mail.enq.subject}")
	private String enqSubject;

	@Override
	public EnquiryVO save(Enquiry enquiry) {
		try {
			Enquiry exist = enquiryRepository.findByForEmailAndMobile(enquiry.getEmail(), enquiry.getMobile());
			if (null == exist) {
				exist = enquiryRepository.save(enquiry);
				MailDetails mailDetails= new MailDetails();
				mailDetails.setMessage("Hi\n\nPlease find below mentioned details for new enquiry:\n%s\n\nThanks");
				mailDetails.setTo(receipients);
				mailDetails.setSubject(enqSubject);
				mailService.triggerEmail(mailDetails);
			}
			return convert(exist);
		} catch (Exception ex) {
			return null;
		}
	}

	private EnquiryVO convert(Enquiry enquiry) {
		final EnquiryVO vo = new EnquiryVO();
		vo.setId(enquiry.getId());
		vo.setName(enquiry.getName());
		vo.setEmail(enquiry.getEmail());
		vo.setMobile(enquiry.getMobile());
		vo.setActive(enquiry.isActive());
		return vo;
	}

}
