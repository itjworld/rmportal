package com.rmportal.dao;

import java.util.List;

import com.rmportal.model.PortalInfo;
import com.rmportal.model.PortalMappingInfo;

public interface InfoServiceDao {

	List<PortalMappingInfo> getDetails(Long[] localities, Integer price, Long acId, Long gender, Long[] rooms);

	List<PortalInfo> getDetails(String type);

}
