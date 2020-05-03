package com.rmportal.transformer;

import java.util.function.Function;

import com.rmportal.model.User;
import com.rmportal.vo.UserDTO;

public class UserTransformer implements Function<User, UserDTO> {

	@Override
	public UserDTO apply(User user) {
		return UserDTO.of(user);
	}

}
