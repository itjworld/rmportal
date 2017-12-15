package com.rmportal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmportal.model.User;
import com.rmportal.repositories.UserRepository;
import com.rmportal.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User validate(User user) {
		return userRepository.validate(user.getUsername(), user.getPassword());
		
	}

}
