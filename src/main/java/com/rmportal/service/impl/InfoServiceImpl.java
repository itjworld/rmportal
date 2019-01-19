package com.rmportal.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rmportal.dao.InfoServiceDao;
import com.rmportal.model.GuestDetail;
import com.rmportal.model.GuestPayment;
import com.rmportal.model.PortalInfo;
import com.rmportal.model.PortalMappingInfo;
import com.rmportal.repositories.AddressRepository;
import com.rmportal.repositories.GuestPaymentRepository;
import com.rmportal.repositories.InfoRepository;
import com.rmportal.repositories.PortalMappingRepository;
import com.rmportal.repositories.RoomBookDetailRepository;
import com.rmportal.service.InfoService;
import com.rmportal.util.PortalMappingComparator;
import com.rmportal.vo.ContactInformationVO;
import com.rmportal.vo.GuestVM;
import com.rmportal.vo.MappingDTO;
import com.rmportal.vo.PortalInformationVO;
import com.rmportal.vo.RecordVO;

@Service
public class InfoServiceImpl implements InfoService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private InfoServiceDao infoServiceDao;

	@Autowired
	private InfoRepository infoRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private PortalMappingRepository portalMappingRepository;

	@Autowired
	private RoomBookDetailRepository roomBookDetailRepository;

	@Autowired
	private GuestPaymentRepository guestPaymentRepository;

	@Autowired
	@PersistenceContext
	private EntityManager entityManager;
	
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
	@Transactional(readOnly = true)
	public List<GuestDetail> findAllByMonth() {
		LocalDate localDate = LocalDate.now();
		return roomBookDetailRepository.findAllByMonth(true, localDate.getMonthValue(), localDate.getYear());
	}

	@Override
	@Transactional(readOnly = false)
	public PortalInfo create(PortalInfo portalInfo) {
		return infoRepository.save(portalInfo);
	}

	private List<PortalInformationVO> convert(List<PortalMappingInfo> details) {
		if (!details.isEmpty()) {
			return details.stream().sorted(new PortalMappingComparator()).map(x -> convertView(x))
					.collect(Collectors.toList());
		}

		return Collections.emptyList();
	}

	private PortalInformationVO convertView(final PortalMappingInfo info) {
		final PortalInformationVO informationVO = new PortalInformationVO();
		informationVO.setId(info.getId());
		informationVO.setDeposit(info.getDeposit());
		informationVO.setRent(info.getRent());
		informationVO.setRoomType(info.getRoomType().getValue());
		informationVO.setRoomNo(info.getRoomNumber() + ". ");
		informationVO.setOccupied(info.getOccupied());
		if (null != info.getGender()) {
			informationVO.setGender(info.getGender().getName());
		}
		if (null != info.getAddress()) {
			informationVO.setStreet1(info.getAddress().getStreet1());
			informationVO.setStreet2(info.getAddress().getStreet2());
			informationVO.setContent(info.getAddress().getContent());
			informationVO.setLocation(info.getAddress().getLocation().getName());
		}
		if (null != info.getCondition()) {
			informationVO.setCondition(info.getCondition().getName());
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
		ContactInformationVO contactInformationVO = new ContactInformationVO();
		if (null != detail) {
			contactInformationVO.setId(detail.getId());
			if (null != detail.getAddress()) {
				contactInformationVO.setMobile(detail.getAddress().getContact());
				contactInformationVO.setStreet1(detail.getAddress().getStreet1());
				contactInformationVO.setStreet2(detail.getAddress().getStreet2());
				contactInformationVO.setLocation(detail.getAddress().getLocation().getName());
				contactInformationVO.setName(detail.getAddress().getName());
			}

		}

		return contactInformationVO;
	}

	@Override
	@Transactional(readOnly = false)
	public PortalMappingInfo save(MappingDTO mapping) {
		PortalMappingInfo roomMapping = portalMappingRepository.getMapping(mapping.getAddressId(), mapping.getRoomNo());
		if (roomMapping != null) {
			throw new RuntimeException("Room already mapped with the given address.");
		}
		PortalMappingInfo mappingRef = new PortalMappingInfo();
		mappingRef.setDeposit(mapping.getSecurity());
		mappingRef.setDesc(mapping.getDesc());
		mappingRef.setOccupied(0);
		mappingRef.setRoomNumber(mapping.getRoomNo());
		mappingRef.setAddress(addressRepository.findOne(mapping.getAddressId()));
		mappingRef.setCondition(infoRepository.findOne(mapping.getAcId()));
		mappingRef.setRoomType(infoRepository.findOne(mapping.getRoomTypeId()));
		mappingRef.setGender(infoRepository.findOne(mapping.getGenderId()));
		mappingRef.setRent(mapping.getRent());
		portalMappingRepository.save(mappingRef);
		return mappingRef;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean execute(String query) {
		try {
			final Query sql = entityManager.createNativeQuery(query);
			sql.executeUpdate();
		} catch (Exception ex) {
			LOGGER.error("exception generated : ", ex);
			return false;
		}
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public RecordVO getRecords(int page, int limit, String sort, String order, String searchParam, Long address) {
		RecordVO recordVO = new RecordVO();
		PageRequest pageRequest = null;
		Page<GuestDetail> records = null;
		LocalDate localDate = LocalDate.now();
		Sort sorting = null;
		if (sort != null && sort.trim().length() > 0) {
			if ("roomNo".equalsIgnoreCase(sort))
				sorting = new Sort(new Sort.Order("ASC".equalsIgnoreCase(order) ? Direction.ASC : Direction.DESC,
						"mapping.roomNumber"));
			else
				sorting = new Sort(
						new Sort.Order("ASC".equalsIgnoreCase(order) ? Direction.ASC : Direction.DESC, sort));
			pageRequest = new PageRequest((page - 1), limit, sorting);
		} else {
			sorting = new Sort(new Sort.Order(Direction.ASC, "mapping.roomNumber"));
			pageRequest = new PageRequest((page - 1), limit, sorting);
		}

		if (searchParam != null && searchParam.trim().length() > 0) {
			if (null != address && address > 0) {
				records = roomBookDetailRepository.findAll(searchParam, searchParam, searchParam, address, pageRequest);
			} else {
				records = roomBookDetailRepository.findAll(searchParam, searchParam, searchParam, pageRequest);
			}
		} else {
			if (null != address && address > 0) {
				records = roomBookDetailRepository.findAllByStatus(pageRequest, true, address,
						localDate.getMonthValue(), localDate.getYear());
			} else {
				records = roomBookDetailRepository.findAllByStatus(pageRequest, true, localDate.getMonthValue(),
						localDate.getYear());
			}
		}
		createResponseVM(page, recordVO, records);
		return recordVO;
	}

	private void createResponseVM(int page, RecordVO recordVO, Page<GuestDetail> records) {
		List<GuestVM> guestDetais = new ArrayList<>();
		GuestVM guest = null;
		for(GuestDetail detail : records) {
			guest = new GuestVM();
			guest.setId(detail.getId());
			guest.setName(String.join(" ", detail.getfName(), detail.getlName()));
			guest.setMobile(detail.getMobile());
			guest.setEmail(detail.getEmail());
			guest.setRent(detail.getRent());
			guest.setSecurity(detail.getSecurity());
			guest.setRoomNo(detail.getRoomNo());
			guest.setCheckindate(detail.getCheckindate());
			guest.setElctricityPaid(detail.getPaymentList().get(0).getElecBillPaid());
			guestDetais.add(guest);
		}
		recordVO.setTotal(records.getTotalElements());
		recordVO.setData(sortRecordsAndAppendSrNo(null, guestDetais, page));
	}

	@Override
	public <T> List<T> getRecords() {
		List<GuestDetail> guestList = roomBookDetailRepository.findByStatus(true);
		Comparator<GuestDetail> roomComparator = (GuestDetail o1, GuestDetail o2) -> o1.getRoomNo()
				.compareTo(o2.getRoomNo());
		return (List<T>) sortRecordsAndAppendSrNo(roomComparator, guestList, 1);
	}

	private <T> List<T> sortRecordsAndAppendSrNo(Comparator<T> roomComparator,
			List<T> guestList, int page) {
		if (roomComparator != null)
			Collections.sort(guestList, roomComparator);
		int i = getInitSrNumber(page);
		for (T guestDetail : guestList) {
			if(guestDetail instanceof GuestVM)
				((GuestVM)guestDetail).setSrNo(i++);
			else
				((GuestDetail)guestDetail).setSrNo(i++);
		}
		return guestList;
	}

	private int getInitSrNumber(int page) {
		int i = 1;
		switch (page) {
		case 2:
			i = 11;
			break;
		case 3:
			i = 21;
			break;
		case 4:
			i = 31;
			break;
		case 5:
			i = 41;
			break;
		case 6:
			i = 51;
			break;
		case 7:
			i = 61;
			break;
		case 8:
			i = 71;
			break;
		}
		return i;
	}

	@Transactional(readOnly = false)
	public <T> boolean updateRecords(T record) {
		GuestVM guest = (GuestVM)record;
		GuestDetail guestDetail = roomBookDetailRepository.findIdAndByMonth(guest.getId(), 1, 2019);
//		if (!record.isActive()) {
//			record.getMapping().setOccupied(record.getMapping().getOccupied() - 1);
//		}
		if(guest.getRoomNo() != guestDetail.getRoomNo())
			guestDetail.setMapping(portalMappingRepository.getMapping(guestDetail.getMapping().getAddress().getId(), guest.getRoomNo()));
		if(guestDetail.getPaymentList().get(0) != null) {
			guestDetail.getPaymentList().get(0).setElecBillPaid(guest.getElctricityPaid());
			guestDetail.getPaymentList().get(0).setRent(guest.getRent());
		}
		guestDetail.setSecurity(guest.getSecurity());
		guestDetail.setMobile(guest.getMobile());
		guestDetail.setEmail(guest.getEmail());
		roomBookDetailRepository.save(guestDetail);
		return true;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteRecords(long id) {
		GuestDetail record = roomBookDetailRepository.findOne(id);
		record.setActive(false);
		roomBookDetailRepository.save(record);
		return true;
	}

	@Override
	public RecordVO getMyRecords(int page, int limit, String sort, String order, String searchParam) {
		RecordVO recordVO = new RecordVO();
		PageRequest pageRequest = null;
		Page<GuestDetail> records = null;
		if (searchParam != null && searchParam.trim().length() > 0) {
			recordVO.setTotal(roomBookDetailRepository.count(searchParam, searchParam, searchParam));
		} else {
			recordVO.setTotal(roomBookDetailRepository.countByStatus(true));
		}

		if (sort != null && sort.trim().length() > 0) {
			Sort sorting = null;
			if ("roomNo".equalsIgnoreCase(sort))
				sorting = new Sort(new Sort.Order("ASC".equalsIgnoreCase(order) ? Direction.ASC : Direction.DESC,
						"mapping.roomNumber"));
			else
				sorting = new Sort(
						new Sort.Order("ASC".equalsIgnoreCase(order) ? Direction.ASC : Direction.DESC, sort));
			pageRequest = new PageRequest((page - 1), limit, sorting);
		} else {
			pageRequest = new PageRequest((page - 1), limit);
		}

		if (searchParam != null && searchParam.trim().length() > 0) {
			records = roomBookDetailRepository.findAll(searchParam, searchParam, searchParam, pageRequest);
		} else {
			records = roomBookDetailRepository.findAllByStatus(pageRequest, true);
		}
		recordVO.setData(records.getContent());
		return recordVO;
	}

	@Override
	public RecordVO getMyRecords(String email) {
		RecordVO recordVO = new RecordVO();
		if (StringUtils.isNotEmpty(email)) {
			recordVO.setData(guestPaymentRepository.findByEmail(email));
			recordVO.setTotal(recordVO.getData().size());
		}
		return recordVO;
	}

	@Override
	public RecordVO getRentDetail(long id) {
		RecordVO recordVO = new RecordVO();
		LocalDate date = LocalDate.now();
		recordVO.setData(guestPaymentRepository.findByGuestDetailId(id, date.getYear(), date.getMonthValue()));
		return recordVO;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean updateRentDetails(final GuestPayment guestPayment) {
		final GuestPayment g = guestPaymentRepository.findOne(guestPayment.getId());
		g.setElecBillPaid(guestPayment.getElecBillPaid());
		g.setRent(guestPayment.getRent());
		guestPaymentRepository.save(g);
		return true;
	}

	@Override
	public PortalInformationVO getDetailsById(long id) {
		return convertView(infoServiceDao.getDetailsById(id));
	}

}
