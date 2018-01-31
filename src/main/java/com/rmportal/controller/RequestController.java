package com.rmportal.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

@RestController
@RequestMapping(value = "/api/v1")
public class RequestController {

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

		return new ResponseEntity<List<PortalInformationVO>>(
				infoService.getDetails(localities, price, acId, gender, rooms), HttpStatus.OK);
	}

	@RequestMapping(value = "/details/{type}")
	public ResponseEntity<List<PortalInfo>> getAllData(@PathVariable String type) {
		System.out.println("Type : " + type);
		return new ResponseEntity<List<PortalInfo>>(infoService.getAllData(type), HttpStatus.OK);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<PortalInfo> create(@RequestBody PortalInfo portalInfo) {

		System.out.println(String.format("create service - >  name : %s :: code : %s :: Type : %s : active : %s",
				portalInfo.getName(), portalInfo.getCode(), portalInfo.getType(), portalInfo.isStatus()));
		return new ResponseEntity<PortalInfo>(infoService.create(portalInfo), HttpStatus.OK);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ResponseEntity<Integer> insert(@RequestBody String query) {

		System.out.println(String.format("insert service - >  query : %s", query));
		return new ResponseEntity<Integer>(infoService.insert(query), HttpStatus.OK);
	}

	@RequestMapping(value = "/enquiry/save", method = RequestMethod.POST)
	public ResponseEntity<EnquiryVO> saveEnquiry(@RequestBody Enquiry enquiry) {

		System.out.println(String.format("saveEnquiry - >  query : %s", enquiry));
		return new ResponseEntity<EnquiryVO>(enquiryService.save(enquiry), HttpStatus.OK);
	}

	@RequestMapping(value = "/address/{cityId}", method = RequestMethod.GET)
	public ResponseEntity<List<AddressInfo>> getAddress(@PathVariable Long cityId) {

		System.out.println(String.format("getAddress - >  cityId : %s", cityId));
		return new ResponseEntity<List<AddressInfo>>(addressService.getAddressByCityId(cityId), HttpStatus.OK);
	}

	@RequestMapping(value = "/contact-information")
	public ResponseEntity<ContactInformationVO> getDetails(@RequestBody @RequestParam(required = true) Long id) {
		return new ResponseEntity<ContactInformationVO>(infoService.getContactInformation(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/mapping/save", method = RequestMethod.POST)
	public ResponseEntity<PortalMappingInfo> saveMapping(@RequestBody MappingDTO mapping) {
		System.out.println(String.format("saveMapping - >  city id : %s", mapping.getCityId()));
		return new ResponseEntity<PortalMappingInfo>(infoService.save(mapping), HttpStatus.OK);
	}

	@RequestMapping(value = "/address/save", method = RequestMethod.POST)
	public ResponseEntity<AddressInfo> saveAddress(@RequestBody ContactInformationVO details) {
		System.out.println(String.format("saveMapping - >  city id : %s", details.getName()));
		return new ResponseEntity<AddressInfo>(addressService.save(details), HttpStatus.OK);
	}

	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public ResponseEntity<User> validate(@RequestBody User user) {
		System.out.println(String.format("validate - >  user : %s", user.getUsername()));
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
		System.out.println(String.format("execute - >  query : %s", query));
		if (StringUtils.isAnyBlank(query))
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		return new ResponseEntity<Boolean>(infoService.execute(query), HttpStatus.OK);
	}

	@RequestMapping(value = "/room/update", method = RequestMethod.POST)
	public ResponseEntity<Boolean> updateRoomInfo(@RequestBody GuestDetail roomBookDetails) {
		System.out.println(String.format("updateRoomInfo - >  mobile : %s", roomBookDetails.getAddressId()));
		return new ResponseEntity<Boolean>(addressService.updateRoomInfo(roomBookDetails), HttpStatus.OK);
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ResponseEntity<Boolean> registration(@RequestBody User user) {
		System.out.println(String.format("registration - > " + user.getfName()));
		return new ResponseEntity<Boolean>(userService.register(user), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/records", method = RequestMethod.GET)
	public ResponseEntity<RecordVO> getRecords(@RequestBody @RequestParam(name="_page",required = false) int page,
			@RequestParam(name="_limit",required = false) int limit,
			@RequestParam(name="_sort",required = false) String sort,
			@RequestParam(name="_order",required = false) String order,@RequestParam(name="_searchParam",required = false) String searchParam) {
		System.out.println(String.format("getRecords"));
		return new ResponseEntity<RecordVO>(infoService.getRecords(page,limit,sort,order,searchParam), HttpStatus.OK);
	}
	
//	@RequestMapping(value = "/myrecords", method = RequestMethod.GET)
//	public ResponseEntity<RecordVO> getMyRecords(@RequestBody @RequestParam(name="_page",required = false) int page,
//			@RequestParam(name="_limit",required = false) int limit,
//			@RequestParam(name="_sort",required = false) String sort,
//			@RequestParam(name="_order",required = false) String order,@RequestParam(name="_searchParam",required = false) String searchParam) {
//		System.out.println(String.format("getMyRecords"));
//		UserDetails userDetails =
//				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		System.err.println(userDetails.getUsername());
//		return new ResponseEntity<RecordVO>(infoService.getMyRecords(page,limit,sort,order,searchParam), HttpStatus.OK);
//	}
	
	@RequestMapping(value = "/myrecords/{username}", method = RequestMethod.GET,  produces = "application/json")
	public ResponseEntity<RecordVO> getMyRecords(@PathVariable String username) {
		System.out.println(String.format("getMyRecords with username : " + username));
		RecordVO RecordVO = infoService.getMyRecords("akk.anilkundu@gmail.com");
		return new ResponseEntity<RecordVO>(RecordVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/guest/update", method = RequestMethod.POST)
	public ResponseEntity<Boolean> updateRecords(@RequestBody GuestDetail record) {
		System.out.println(String.format("updateRecords - > %s %s %s %s %s %s", record.getfName(), record.getAddress(), record.getRent(), record.getAddressId(), record.getEmail(), record.getRoomNo()));
		boolean status = infoService.updateRecords(record);
		return new ResponseEntity<Boolean>(status, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/guest/delete", method = RequestMethod.POST)
	public ResponseEntity<Boolean> deleteRecords(@RequestBody GuestDetail record) {
		System.out.println(String.format("deleteRecords - > %s %s %s %s %s %s", record.getId(), record.getAddress(), record.getRent(), record.getAddressId(), record.getEmail(), record.getRoomNo()));
		boolean status = infoService.deleteRecords(record.getId());
		return new ResponseEntity<Boolean>(status, HttpStatus.OK);
	}
	
}
