package com.rmportal.service.impl;

import java.util.List;

import org.hibernate.type.TrueFalseType;
import org.jetbrains.annotations.ReadOnly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rmportal.dao.InfoServiceDao;
import com.rmportal.model.PortalInfo;
import com.rmportal.repositories.InfoRepository;
import com.rmportal.service.InfoService;

@Service
public class InfoServiceImpl implements InfoService {

	@Autowired
	private InfoServiceDao infoServiceDao;

	@Autowired
	private InfoRepository infoRepository;

	@Override
	public void getDetails(Long[] localities, Integer price, Long acId, Long gender, Long[] rooms) {
		infoServiceDao.getDetails(localities, price, acId, gender, rooms);
	}

	@Override
	@Transactional(readOnly=true)
	public List<PortalInfo> getAllData(String type) {
		return infoRepository.findByType(type);
	}

	@Override
	@Transactional(readOnly=false)
	public PortalInfo create(PortalInfo portalInfo) {
		return infoRepository.save(portalInfo);
	}

}
