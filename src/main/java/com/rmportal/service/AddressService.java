package com.rmportal.service;

import java.util.List;

import com.rmportal.model.AddressInfo;
import com.rmportal.model.GuestDetail;
import com.rmportal.vo.ContactInformationVO;
import com.rmportal.vo.ResponseMessage;

public interface AddressService {

	List<AddressInfo> getAddressByCityId(Long cityId);

	AddressInfo save(ContactInformationVO details);

	ResponseMessage updateRoomInfo(GuestDetail roomBookDetails);
	
	List<AddressInfo> getAddressInformation();

	
}
