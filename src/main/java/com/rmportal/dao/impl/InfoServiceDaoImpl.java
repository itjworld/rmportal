package com.rmportal.dao.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rmportal.dao.InfoServiceDao;
import com.rmportal.model.PortalInfo;

@Service
public class InfoServiceDaoImpl implements InfoServiceDao{
	
	@Override
	public void getDetails(Long[] localities, Integer price, Long acId, Long gender, Long[] rooms) {
		
	}

	@Override
	public List<PortalInfo> getDetails(String type) {
		return null;
	}
}
