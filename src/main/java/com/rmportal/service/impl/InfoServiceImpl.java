package com.rmportal.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rmportal.dao.InfoServiceDao;
import com.rmportal.model.PortalInfo;
import com.rmportal.model.PortalMappingInfo;
import com.rmportal.repositories.InfoRepository;
import com.rmportal.repositories.PortalMappingRepository;
import com.rmportal.service.InfoService;
import com.rmportal.vo.ContactInformationVO;
import com.rmportal.vo.PortalInformationVO;

@Service
public class InfoServiceImpl implements InfoService {

	@Autowired
	private InfoServiceDao infoServiceDao;

	@Autowired
	private InfoRepository infoRepository;
	
	@Autowired
	private PortalMappingRepository  portalMappingRepository;
	

	@Override
	@Transactional(readOnly = true)
	public List<PortalInformationVO> getDetails(Long[] localities, Integer price, Long acId, Long gender,
			Long[] rooms) {
		return convert(infoServiceDao.getDetails(localities, price, acId, gender, rooms));
	}

	@Override
	@Transactional(readOnly = true)
	public List<PortalInfo> getAllData(String type) {
		return infoRepository.findByType(type);
	}

	@Override
	@Transactional(readOnly = false)
	public PortalInfo create(PortalInfo portalInfo) {
		return infoRepository.save(portalInfo);
	}

	private List<PortalInformationVO> convert(List<PortalMappingInfo> details) {
		if (!details.isEmpty()) {
			return details.stream().map(x -> convertView(x)).collect(Collectors.toList());
		}

		return Collections.emptyList();
	}

	private PortalInformationVO convertView(final PortalMappingInfo info) {
		final PortalInformationVO informationVO = new PortalInformationVO();
		informationVO.setId(info.getId());
		informationVO.setDeposit(info.getDeposit());
		informationVO.setRent(info.getRent());
		informationVO.setRoomType(info.getRoomType().getValue());
		informationVO.setOccupied(info.getOccupied());
		if(null!=info.getGender()){
			informationVO.setGender(info.getGender().getName());
		}
		if (null != info.getAddress()) {
			informationVO.setStreet1(info.getAddress().getStreet1());
			informationVO.setStreet2(info.getAddress().getStreet2());
			informationVO.setContent(info.getAddress().getContent());
		}
		return informationVO;
	}

	@Transactional(readOnly = false)
	public Integer insert(String query) {
		return infoServiceDao.create(query);
	}

	@Transactional(readOnly = true)
	@Override
	public ContactInformationVO getContactInformation(long id) {
		return convert(portalMappingRepository.getOne(id));
	}
	private ContactInformationVO convert(PortalMappingInfo detail) {
		ContactInformationVO contactInformationVO=new ContactInformationVO();
		if(null!=detail){
			contactInformationVO.setId(detail.getId());
			if(null!=detail.getAddress()){
				contactInformationVO.setMobile(detail.getAddress().getContact());
				contactInformationVO.setStreet1(detail.getAddress().getStreet1());
				contactInformationVO.setStreet2(detail.getAddress().getStreet2());
				contactInformationVO.setLocation(detail.getAddress().getLocation().getName());
			}
			
		}

		return contactInformationVO;
	}

}
