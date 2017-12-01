package com.rmportal.service;

import java.util.List;

import com.rmportal.model.AddressInfo;

public interface AddressService {

	List<AddressInfo> getAddressByCityId(Long cityId);
	
}
