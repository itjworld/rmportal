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
		
		GuestVM guest = new GuestVM();
		guest.setId(7);
		guest.setName("Janny");
		guest.setElctricityPaid(444);
		guest.setRent(4000);
		guest.setRoomNo(9);
		infoService.updateRecords(guest);
		
		System.out.println("#######finish#######");
	}
}
