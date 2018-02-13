package com.rmportal.service;

import com.rmportal.model.User;
import com.rmportal.vo.ResponseMessage;

public interface UserService {

	User validate(User user);

	ResponseMessage register(User user);
}
