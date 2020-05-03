package com.rmportal.service;

import com.rmportal.model.User;
import com.rmportal.vo.RecordVO;
import com.rmportal.vo.ResponseMessage;
import com.rmportal.vo.UserDTO;


public interface UserService {

	User validate(User user);

	ResponseMessage register(User user);
	
	RecordVO<UserDTO> getUsers();

	boolean updateUser(UserDTO record);
}
