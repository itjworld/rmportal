package com.rmportal.service.impl;

import static com.rmportal.util.Utility.updateResponse;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rmportal.model.GuestDetail;
import com.rmportal.model.Role;
import com.rmportal.model.User;
import com.rmportal.repositories.RoleRepository;
import com.rmportal.repositories.RoomBookDetailRepository;
import com.rmportal.repositories.UserRepository;
import com.rmportal.service.UserService;
import com.rmportal.vo.RecordVO;
import com.rmportal.vo.ResponseMessage;
import com.rmportal.vo.UserDTO;

@Service
public class UserServiceImpl implements UserService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private RoomBookDetailRepository roomBookDetailRepository;

	@Override
	public User validate(User user) {
		return userRepository.validate(user.getUsername(), user.getPassword());

	}

	@Override
	public ResponseMessage register(User user) {
		try {
			if (userRepository.findByUsername(user.getUsername(), user.getEmail()) != null)
				return updateResponse("Username already registered", false);
			GuestDetail guestDetail = roomBookDetailRepository.findByEmail(user.getEmail());
			if (guestDetail != null && guestDetail.getEmail().equalsIgnoreCase(user.getEmail())) {
				user.setStatus(true);
				Set<Role> roles = new HashSet<>();
				roles.add(roleRepository.findByName("user"));
				user.setRoles(roles);
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
	
	public RecordVO<UserDTO> getUsers(){
		List<UserDTO> userDTOs =  userRepository.findAll().stream().map(UserDTO::of).collect(Collectors.toList());
		return RecordVO.of(userDTOs, userDTOs.size());
	}

	@Override
	@Transactional
	public boolean updateUser(UserDTO record) {
		User user = userRepository.findOne(record.getId());
		user.setStatus(record.isStatus());
		return false;
	}


}
