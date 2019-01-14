package com.rmportal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rmportal.model.GuestDetail;
import com.rmportal.model.GuestPayment;
import com.rmportal.repositories.GuestPaymentRepository;
import com.rmportal.repositories.RoomBookDetailRepository;
import com.rmportal.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private RoomBookDetailRepository roomBookDetailRepository;

	@Autowired
	private GuestPaymentRepository guestPaymentRepository;

	@Override
	public boolean insertMonthRecords() {
		List<GuestDetail> guestList = roomBookDetailRepository.findByStatus(true);
		saveNewMonthRecords(guestList);
		return false;
	}

	@Transactional
	private void saveNewMonthRecords(List<GuestDetail> guestList) {
		GuestPayment payment = null;
		List<GuestPayment> guestPaymentList = new ArrayList<GuestPayment>();
		for (GuestDetail guestDetail : guestList) {
			payment = new GuestPayment();
			payment.setGuestDetail(guestDetail);
			guestPaymentList.add(payment);
			guestPaymentRepository.save(payment);
		}

	}

}
