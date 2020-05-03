package com.rmportal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rmportal.service.UserService;
import com.rmportal.vo.RecordVO;
import com.rmportal.vo.UserDTO;

@RestController
@RequestMapping(value = "/api/v1")
public class UserController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public UserService userService;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<RecordVO<?>> getUsers() {
		LOGGER.debug("inside method getUsers");
		return new ResponseEntity<RecordVO<?>>(userService.getUsers(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	public ResponseEntity<Boolean> updateUser(@RequestBody UserDTO record) {
		LOGGER.debug("updateRecords - > username:{} :: fName:{} :: lName:{} :: email:{} :: active:{}", record.getUsername(), record.getfName(), record.getlName(), record.getEmail(), record.isStatus());
		return new ResponseEntity<Boolean>(userService.updateUser(record), HttpStatus.OK);
	}

}
