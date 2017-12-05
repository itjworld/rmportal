package com.rmportal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmportal.model.Enquiry;
import com.rmportal.model.PortalMappingInfo;
import com.rmportal.repositories.EnquiryRepository;
import com.rmportal.service.EnquiryService;
import com.rmportal.vo.EnquiryVO;
import com.rmportal.vo.MappingDTO;

@Service
public class EnquiryServiceImpl implements EnquiryService {

	@Autowired
	private EnquiryRepository enquiryRepository;

	@Override
	public EnquiryVO save(Enquiry enquiry) {
		Enquiry exist=enquiryRepository.findByForEmailAndMobile(enquiry.getEmail(), enquiry.getMobile());
		if(null==exist){
			exist=enquiryRepository.save(enquiry);
		}
		return convert(exist);
	}

	private EnquiryVO convert(Enquiry enquiry) {
		final EnquiryVO vo= new EnquiryVO();
		vo.setId(enquiry.getId());
		vo.setName(enquiry.getName());
		vo.setEmail(enquiry.getEmail());
		vo.setMobile(enquiry.getMobile());
		vo.setActive(enquiry.isActive());
		return vo;
	}

}
