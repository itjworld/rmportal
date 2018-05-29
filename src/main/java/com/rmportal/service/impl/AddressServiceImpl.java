package com.rmportal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rmportal.model.AddressInfo;
import com.rmportal.model.GuestDetail;
import com.rmportal.model.GuestPayment;
import com.rmportal.model.PortalInfo;
import com.rmportal.model.PortalMappingInfo;
import com.rmportal.repositories.AddressRepository;
import com.rmportal.repositories.GuestPaymentRepository;
import com.rmportal.repositories.InfoRepository;
import com.rmportal.repositories.PortalMappingRepository;
import com.rmportal.repositories.RoomBookDetailRepository;
import com.rmportal.service.AddressService;
import com.rmportal.vo.ContactInformationVO;
import com.rmportal.vo.ResponseMessage;
import static com.rmportal.util.Utility.*;

@Service
public class AddressServiceImpl implements AddressService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private InfoRepository infoRepository;

	@Autowired
	PortalMappingRepository portalMappingRepository;

	@Autowired
	private RoomBookDetailRepository roomBookDetailRepository;
	
	@Autowired
	private GuestPaymentRepository guestPaymentRepository;

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
	public ResponseMessage updateRoomInfo(GuestDetail guestDetail) {
		try {
			PortalMappingInfo mapping = portalMappingRepository.getMapping(guestDetail.getAddressId(),
					guestDetail.getRoomNo());
			if (mapping != null) {
				GuestDetail guest = roomBookDetailRepository.findByEmail(guestDetail.getEmail());
				if(guest != null){
					return updateResponse("Email id already configured.", false);
				}
				guestDetail.setMapping(mapping);
				mapping.setOccupied(mapping.getOccupied() + 1);
				roomBookDetailRepository.save(guestDetail);
				GuestPayment guestPayment = new GuestPayment();
				guestPayment.setRent(guestDetail.getRent());
				guestPayment.setGuestDetail(guestDetail);
				guestPaymentRepository.save(guestPayment);
			} else
				return updateResponse("Room mapping not found with address", false);
			return updateResponse("Room Booked Successfully.", true);
		} catch (Exception ex) {
			LOGGER.error("updateRoomInfo",ex);
			return updateResponse("Problem exists while booking room.", false);
		}
	}

	@Override
	public List<AddressInfo> getAddressInformation() {
		final List<AddressInfo> addressInfos= new ArrayList<AddressInfo>();
		final List<?> list=addressRepository.findAll();
		list.forEach(x->{
			Object [] o =(Object [])x;
			AddressInfo addressInfo= new AddressInfo();
			addressInfo.setStreet2((String)o[0]);
			addressInfo.setId((Long)o[1]);
			addressInfos.add(addressInfo);
		});
		return addressInfos;
	}

}
