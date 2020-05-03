package com.rmportal.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rmportal.model.GuestDetail;
import com.rmportal.model.GuestPayment;
import com.rmportal.model.MonthRecords;
import com.rmportal.model.MonthRecords.MonthRecordsBuilder;
import com.rmportal.repositories.GuestPaymentRepository;
import com.rmportal.repositories.MonthReordsRepository;
import com.rmportal.repositories.RoomBookDetailRepository;
import com.rmportal.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private RoomBookDetailRepository roomBookDetailRepository;

	@Autowired
	private GuestPaymentRepository guestPaymentRepository;

	@Autowired
	private MonthReordsRepository monthReordsRepository;

	@Transactional(readOnly = false)
	public boolean insertMonthRecords() {
		LocalDate date = LocalDate.now();
		int count = monthReordsRepository.findByMonth(date.getMonthValue(), date.getYear());
		if (count != 0)
			return false;
		GuestPayment payment = null;
		List<GuestDetail> guestList = roomBookDetailRepository.findByStatus(true);
		List<GuestPayment> guestPaymentList = new ArrayList<GuestPayment>();
		for (GuestDetail guestDetail : guestList) {
			payment = new GuestPayment();
			payment.setGuestDetail(guestDetail);
			guestPaymentList.add(payment);
			guestPaymentRepository.save(payment);
		}
		monthReordsRepository.save(MonthRecords.builder().month(date.getMonthValue()).year(date.getYear()).build());
		return true;
	}

}
