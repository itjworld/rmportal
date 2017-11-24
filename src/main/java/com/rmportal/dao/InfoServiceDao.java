package com.rmportal.dao;

import java.util.List;

import com.rmportal.model.PortalInfo;

public interface InfoServiceDao {

	void getDetails(Long[] localities, Integer price, Long acId, Long gender, Long[] rooms);

	List<PortalInfo> getDetails(String type);

}
