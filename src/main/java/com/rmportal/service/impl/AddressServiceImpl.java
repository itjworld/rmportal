package com.rmportal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmportal.model.AddressInfo;
import com.rmportal.model.PortalInfo;
import com.rmportal.repositories.AddressRepository;
import com.rmportal.repositories.InfoRepository;
import com.rmportal.service.AddressService;
import com.rmportal.vo.ContactInformationVO;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private InfoRepository infoRepository;

	@Override
	public List<AddressInfo> getAddressByCityId(Long cityId) {
		PortalInfo info = new PortalInfo();
		info.setId(cityId);
		return addressRepository.findByLocation(info);
	}

	@Override
	public AddressInfo save(ContactInformationVO details) {
		AddressInfo address = new AddressInfo();
		address.setContact(details.getMobile());
		address.setEmail(details.getEmail());
		address.setName(details.getName());
		address.setStreet1(details.getStreet1());
		address.setStreet2(details.getStreet2());
		address.setLocation(infoRepository.findOne(Long.parseLong(details.getLocation())));
		addressRepository.save(address);
		return address;
	}

	

}
