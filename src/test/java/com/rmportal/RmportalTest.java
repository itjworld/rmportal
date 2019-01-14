package com.rmportal;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.rmportal.model.GuestDetail;
import com.rmportal.repositories.RoomBookDetailRepository;
import com.rmportal.service.InfoService;
import com.rmportal.service.PaymentService;

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
	@Transactional
	public void testGetCompletedJobs() throws ParseException {
		System.out.println("##################");
//		infoService.findAllByMonth().forEach(e -> System.out.println(e+ " - " + e.getfName()));
		LocalDate localDate = LocalDate.now();
		List<GuestDetail> list = roomBookDetailRepository.findAllByMonth(true, localDate.getMonthValue(),
				localDate.getYear());
		list.stream().forEach(e -> {
			System.out.println("@@@@@@ " + e.getfName());
			e.getPaymentList().stream().forEach(m -> System.out.println("@@@@@@ " + m.getCurrentMonth()));
		});
//		paymentService.insertMonthRecords();
		System.out.println("#######finish#######");
	}
}
