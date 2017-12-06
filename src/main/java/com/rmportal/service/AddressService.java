package com.rmportal.service;

import java.util.List;

import com.rmportal.model.AddressInfo;
import com.rmportal.vo.ContactInformationVO;

public interface AddressService {

	List<AddressInfo> getAddressByCityId(Long cityId);

	AddressInfo save(ContactInformationVO details);
	
}
