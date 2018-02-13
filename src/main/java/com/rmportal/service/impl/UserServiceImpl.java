package com.rmportal.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmportal.model.GuestDetail;
import com.rmportal.model.User;
import com.rmportal.repositories.RoomBookDetailRepository;
import com.rmportal.repositories.UserRepository;
import com.rmportal.service.UserService;
import com.rmportal.vo.ResponseMessage;

@Service
public class UserServiceImpl implements UserService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoomBookDetailRepository roomBookDetailRepository;

	@Override
	public User validate(User user) {
		return userRepository.validate(user.getUsername(), user.getPassword());

	}

	@Override
	public ResponseMessage register(User user) {
		try {
			if (userRepository.findByUsername(user.getUsername()) != null)
				return updateResponse("Username already registered", false);
			GuestDetail guestDetail = roomBookDetailRepository.findByEmail(user.getEmail());
			if (guestDetail != null) {
				if (guestDetail.getEmail().equalsIgnoreCase(user.getEmail()))
					return updateResponse("User already registered with email id", false);
				userRepository.save(user);
				return updateResponse("User Registered Successfully", true);
			} else {
				return updateResponse("Please enter registered email id", false);
			}
		} catch (Exception ex) {
			LOGGER.error("register", ex);
			return updateResponse("Problem exists while registration", false);
		}
	}

	private ResponseMessage updateResponse(String message, boolean status) {
		return new ResponseMessage(message, status);
	}

}
