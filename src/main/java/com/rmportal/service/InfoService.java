package com.rmportal.service;

import java.util.List;

import com.rmportal.model.PortalInfo;

public interface InfoService {

	void getDetails(Long[] localities, Integer price, Long acId, Long gender, Long[] rooms);

	List<PortalInfo> getAllData(String type);

	PortalInfo create(PortalInfo portalInfo);

}
