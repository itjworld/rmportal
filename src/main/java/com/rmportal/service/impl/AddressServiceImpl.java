package com.rmportal.service.impl;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rmportal.model.AddressInfo;
import com.rmportal.model.PortalInfo;
import com.rmportal.model.PortalMappingInfo;
import com.rmportal.model.RoomBookDetails;
import com.rmportal.repositories.AddressRepository;
import com.rmportal.repositories.InfoRepository;
import com.rmportal.repositories.PortalMappingRepository;
import com.rmportal.repositories.RoomBookDetailRepository;
import com.rmportal.service.AddressService;
import com.rmportal.vo.ContactInformationVO;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private InfoRepository infoRepository;

	@Autowired
	PortalMappingRepository portalMappingRepository;

	@Autowired
	private RoomBookDetailRepository roomBookDetailRepository;

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

	@Override
	@Transactional
	public boolean updateRoomInfo(RoomBookDetails roomBookDetails) {
		try {
			PortalMappingInfo mapping = portalMappingRepository.getMapping(roomBookDetails.getAddressId(),
					roomBookDetails.getRoomNo());
			if (mapping != null) {
				roomBookDetails.setMapping(mapping);
				mapping.setOccupied(mapping.getOccupied() + 1);
				roomBookDetailRepository.save(roomBookDetails);
				// portalMappingRepository.save(mapping);
			} else
				return false;
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

}
