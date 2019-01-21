package com.rmportal;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.rmportal.repositories.RoomBookDetailRepository;
import com.rmportal.service.InfoService;
import com.rmportal.service.PaymentService;
import com.rmportal.vo.GuestVM;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootWebInitApp.class)
@ActiveProfiles(profiles = { "dev" })
public class RmportalTest {

	@Autowired
	private InfoService infoService;

	@Autowired
	private RoomBookDetailRepository roomBookDetailRepository;

	@Autowired
	private PaymentService paymentService;

	@Test
	public void testGetCompletedJobs() throws ParseException {
		System.out.println("##################");
		
		paymentService.insertMonthRecords();
		
		System.out.println("#######finish#######");
	}
}
