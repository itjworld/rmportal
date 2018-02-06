package com.rmportal.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmportal.model.User;
import com.rmportal.repositories.UserRepository;
import com.rmportal.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public User validate(User user) {
		return userRepository.validate(user.getUsername(), user.getPassword());

	}

	@Override
	public boolean register(User user) {
		try {
			userRepository.save(user);
		} catch (Exception ex) {
			LOGGER.error("register",ex);
			return false;
		}
		return true;
	}

}
