package com.rmportal.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rmportal.model.AddressInfo;
import com.rmportal.model.Enquiry;
import com.rmportal.model.GuestDetail;
import com.rmportal.model.PortalInfo;
import com.rmportal.model.PortalMappingInfo;
import com.rmportal.model.User;
import com.rmportal.service.AddressService;
import com.rmportal.service.EnquiryService;
import com.rmportal.service.InfoService;
import com.rmportal.service.UserService;
import com.rmportal.vo.ContactInformationVO;
import com.rmportal.vo.EnquiryVO;
import com.rmportal.vo.MappingDTO;
import com.rmportal.vo.PortalInformationVO;
import com.rmportal.vo.RecordVO;
import com.rmportal.vo.ResponseMessage;

@RestController
@RequestMapping(value = "/api/v1")
public class RequestController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private InfoService infoService;

	@Autowired
	private EnquiryService enquiryService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/details")
	public ResponseEntity<List<PortalInformationVO>> getDetails(
			@RequestBody @RequestParam(required = false) Long[] localities,
			@RequestParam(required = false) Integer price, @RequestParam(required = false) Long acId,
			@RequestParam(required = false) Long gender, @RequestParam(required = false) Long[] rooms) {
		
		LOGGER.debug("detail--->Location:{} :: Price:{} :: Air Condition:{} :: Gender:{} :: Room Type: {}",localities,price,acId,gender,rooms);
		return new ResponseEntity<List<PortalInformationVO>>(
				infoService.getDetails(localities, price, acId, gender, rooms), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/room-details")
	public ResponseEntity<PortalInformationVO> getRoomDetails(@RequestBody @RequestParam(required = true) long id){
		return new ResponseEntity<PortalInformationVO>(infoService.getDetailsById(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/details/{type}")
	public ResponseEntity<List<PortalInfo>> getAllData(@PathVariable String type) {
		LOGGER.debug("Type---{}",type);
		return new ResponseEntity<List<PortalInfo>>(infoService.getAllData(type), HttpStatus.OK);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<PortalInfo> create(@RequestBody PortalInfo portalInfo) {
		LOGGER.debug("create service - >  name : {} :: code : {} :: Type : {} : active : {}",portalInfo.getName(), portalInfo.getCode(), portalInfo.getType(), portalInfo.isStatus());
		return new ResponseEntity<PortalInfo>(infoService.create(portalInfo), HttpStatus.OK);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody String query,@RequestHeader(name="cred",required=true) String cred) {
		if(! "NCRPHASE".equalsIgnoreCase(cred)){
			return new ResponseEntity<String>("Not-Authorized", HttpStatus.OK);
		}
		LOGGER.debug("insert service - >  query :{}",query);
		return new ResponseEntity<Integer>(infoService.insert(query), HttpStatus.OK);
	}

	@RequestMapping(value = "/enquiry/save", method = RequestMethod.POST)
	public ResponseEntity<EnquiryVO> saveEnquiry(@RequestBody Enquiry enquiry) {
		LOGGER.debug("insert saveEnquiry - >  query :{}",enquiry);
		return new ResponseEntity<EnquiryVO>(enquiryService.save(enquiry), HttpStatus.OK);
	}

	@RequestMapping(value = "/address/{cityId}", method = RequestMethod.GET)
	public ResponseEntity<List<AddressInfo>> getAddress(@PathVariable Long cityId) {
		LOGGER.debug("getAddress - >  cityId  :{}",cityId);
		return new ResponseEntity<List<AddressInfo>>(addressService.getAddressByCityId(cityId), HttpStatus.OK);
	}

	@RequestMapping(value = "/contact-information")
	public ResponseEntity<ContactInformationVO> getDetails(@RequestBody @RequestParam(required = true) Long id) {
		LOGGER.debug("Contatct Infomration By Id - > :{}",id);
		return new ResponseEntity<ContactInformationVO>(infoService.getContactInformation(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/mapping/save", method = RequestMethod.POST)
	public ResponseEntity<PortalMappingInfo> saveMapping(@RequestBody MappingDTO mapping) {
		LOGGER.debug("saveMapping - >  city id - > :{}",mapping.getCityId());
		return new ResponseEntity<PortalMappingInfo>(infoService.save(mapping), HttpStatus.OK);
	}

	@RequestMapping(value = "/address/save", method = RequestMethod.POST)
	public ResponseEntity<AddressInfo> saveAddress(@RequestBody ContactInformationVO details) {
		LOGGER.debug("saveMapping - >  city id  :{}",details.getName());
		return new ResponseEntity<AddressInfo>(addressService.save(details), HttpStatus.OK);
	}

	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public ResponseEntity<User> validate(@RequestBody User user) {
		LOGGER.debug("validate - >  user   :{}",user.getUsername());
		user = userService.validate(user);
		if (user == null)
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		else {
			user.setPassword(null);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/execute", method = RequestMethod.POST)
	public ResponseEntity<Boolean> execute(@RequestBody String query) {
		LOGGER.debug("execute - >  query :{}",query);
		if (StringUtils.isAnyBlank(query))
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		return new ResponseEntity<Boolean>(infoService.execute(query), HttpStatus.OK);
	}

	@RequestMapping(value = "/room/update", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> updateRoomInfo(@RequestBody GuestDetail roomBookDetails) {
		LOGGER.debug("updateRoomInfo - >  mobile:{}",roomBookDetails.getAddressId());
		return new ResponseEntity<ResponseMessage>(addressService.updateRoomInfo(roomBookDetails), HttpStatus.OK);
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> registration(@RequestBody User user) {
		LOGGER.debug("registration - >:{}",user.getfName());
		return new ResponseEntity<ResponseMessage>(userService.register(user), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/records/{address}", method = RequestMethod.GET)
	public ResponseEntity<RecordVO> getRecords(@RequestBody @RequestParam(name="_page",required = false) int page,
			@RequestParam(name="_limit",required = false) int limit,
			@RequestParam(name="_sort",required = false) String sort,
			@RequestParam(name="_order",required = false) String order,@RequestParam(name="_searchParam",required = false) String searchParam,@PathVariable Long address) {
		LOGGER.debug("getRecords - >:Page:{} :: Limit:{} :: Sort:{} :: Order:{} :: searchParam:{} :: address:{}",page,limit,sort,order,searchParam,address );
		return new ResponseEntity<RecordVO>(infoService.getRecords(page,limit,sort,order,searchParam,address), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/myrecords", method = RequestMethod.POST)
	public ResponseEntity<RecordVO> getMyRecords(@RequestBody String username) {
		LOGGER.debug("getMyRecords with username : {}" + username);
		return new ResponseEntity<RecordVO>(infoService.getMyRecords(username), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/guest/update", method = RequestMethod.POST)
	public ResponseEntity<Boolean> updateRecords(@RequestBody GuestDetail record) {
		LOGGER.debug("updateRecords - > fName:{} :: Address:{} :: Rent:{} :: Address Id:{} :: Email:{} :: Room No:{}", record.getfName(), record.getAddress(), record.getRent(), record.getAddressId(), record.getEmail(), record.getRoomNo());
		boolean status = infoService.updateRecords(record);
		return new ResponseEntity<Boolean>(status, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/guest/delete", method = RequestMethod.POST)
	public ResponseEntity<Boolean> deleteRecords(@RequestBody GuestDetail record) {
		LOGGER.debug("deleteRecords - > {}", record.getId());
		boolean status = infoService.deleteRecords(record.getId());
		return new ResponseEntity<Boolean>(status, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rent-detail", method = RequestMethod.GET)
	public ResponseEntity<RecordVO> getRentDetails(@RequestParam(name="id",required = true) long id) {
		LOGGER.debug("Get Rent Dteail by Id - > {}", id);
		return new ResponseEntity<RecordVO>(infoService.getRentDetail(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rent-detail/update", method = RequestMethod.POST)
	public ResponseEntity<Boolean> getRentDetails(@RequestBody com.rmportal.model.GuestPayment guestPayment) {
		LOGGER.debug("Get Rent Dteail update - > {}", guestPayment.getElectricBill());
		return new ResponseEntity<Boolean>(infoService.updateRentDetails(guestPayment), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/address/detail", method = RequestMethod.GET)
	public ResponseEntity<List<AddressInfo>> getAddressDetails() {
		return new ResponseEntity<List<AddressInfo>>(addressService.getAddressInformation(), HttpStatus.OK);
	}
	
	
	
}
