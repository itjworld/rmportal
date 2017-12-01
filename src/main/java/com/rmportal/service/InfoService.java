package com.rmportal.service;

import java.util.List;

import com.rmportal.model.PortalInfo;
import com.rmportal.vo.ContactInformationVO;
import com.rmportal.vo.PortalInformationVO;

public interface InfoService {

	List<PortalInformationVO> getDetails(Long[] localities, Integer price, Long acId, Long gender, Long[] rooms);

	List<PortalInfo> getAllData(String type);

	PortalInfo create(PortalInfo portalInfo);

	Integer insert(String query);
	
	ContactInformationVO getContactInformation(long id);

}
