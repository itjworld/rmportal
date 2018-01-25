package com.rmportal.service;

import java.util.List;

import com.rmportal.model.PortalInfo;
import com.rmportal.model.PortalMappingInfo;
import com.rmportal.model.RoomBookDetails;
import com.rmportal.vo.ContactInformationVO;
import com.rmportal.vo.MappingDTO;
import com.rmportal.vo.PortalInformationVO;
import com.rmportal.vo.RecordVO;

public interface InfoService {

	List<PortalInformationVO> getDetails(Long[] localities, Integer price, Long acId, Long gender, Long[] rooms);

	List<PortalInfo> getAllData(String type);

	PortalInfo create(PortalInfo portalInfo);

	Integer insert(String query);
	
	ContactInformationVO getContactInformation(long id);

	PortalMappingInfo save(MappingDTO mapping);

	boolean execute(String query);
	List<RoomBookDetails> getRecords();
	RecordVO getRecords(int page,int limit,String sort,String order,String searchParam);

}
