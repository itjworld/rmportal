package com.rmportal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmportal.model.AddressInfo;
import com.rmportal.model.PortalInfo;
import com.rmportal.repositories.AddressRepository;
import com.rmportal.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public List<AddressInfo> getAddressByCityId(Long cityId) {
		PortalInfo info = new PortalInfo();
		info.setId(cityId);
		return addressRepository.findByLocation(info);
	}

	

}
