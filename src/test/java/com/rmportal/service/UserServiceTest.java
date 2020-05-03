package com.rmportal.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.rmportal.SpringBootWebInitApp;
import com.rmportal.vo.RecordVO;
import com.rmportal.vo.UserDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootWebInitApp.class)
@ActiveProfiles(profiles = { "dev" })
public class UserServiceTest {

	@Autowired
	public UserService userService;

	@Test
	public void testGetUsers() {
		RecordVO<UserDTO> users = userService.getUsers();
		users.getData().forEach(e -> System.out.println(e.getUsername()));
	}

}
