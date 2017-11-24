package com.rmportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rmportal.model.PortalInfo;
import com.rmportal.service.InfoService;

@RestController
@RequestMapping(value = "/api/v1")
public class RequestController {

	@Autowired
	private InfoService infoService;

	@RequestMapping(value = "/details")
	public void getDetails(@RequestBody @RequestParam(required = false) Long[] localities,
			@RequestParam(required = false) Integer price, @RequestParam(required = false) Long acId,
			@RequestParam(required = false) Long gender, @RequestParam(required = false) Long[] rooms) {

		System.out.println(String.format("localities : %s :: price : %s :: id : %s : gender id : %s :: room ids : %s",
				localities, price, acId, gender, rooms));
		infoService.getDetails(localities, price, acId, gender, rooms);
	}

	@RequestMapping(value = "/details/{type}")
	public List<PortalInfo> getAllData(@PathVariable String type) {
		System.out.println("Type : " + type);
		return infoService.getAllData(type);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<PortalInfo> create(@RequestBody PortalInfo portalInfo) {

		System.out.println(String.format("create service - >  name : %s :: code : %s :: Type : %s : active : %s",
				portalInfo.getName(), portalInfo.getCode(), portalInfo.getType(), portalInfo.isStatus()));
		return new ResponseEntity<PortalInfo>(infoService.create(portalInfo), HttpStatus.OK);
	}

}
