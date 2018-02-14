package com.rmportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rmportal.service.InfoService;

@Controller
@RequestMapping(value = "/api/v1")
public class DownloadController {

	@Autowired
	private InfoService infoService;

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ModelAndView download(Model model) {
		String headerName[] = { "Id", "Name", "Mobile", "Email", "Rent", "Security" };
		String headerNameCSV[] = { "Id", "fName", "Mobile", "Email", "Rent", "Security" };
		String fields[] = { "id", "fName", "mobile", "email", "mapping.rent", "security" };
		model.addAttribute("fileName", "records");
		model.addAttribute("headerName", headerName);
		model.addAttribute("headerNameCSV", headerNameCSV);
		model.addAttribute("fields", fields);
		model.addAttribute("sheetName", "Room Book Details");
		model.addAttribute("data", infoService.getRecords());
		return new ModelAndView("excelView");
	}

	@RequestMapping(value = "/mr/download", method = RequestMethod.GET)
	public ModelAndView myRecords(Model model,@RequestParam(required = true) String username) {
		String headerName[] = { "Id", "Rent", "Electricity Paid", "Electricity Bill", "Security", "Month" };
		String headerNameCSV[] = { "Id", "Rent", "ElecBillPaid", "ElectricBill", "Security", "CurrentMonth" };
		String fields[] = { "id", "rent", "elecBillPaid", "electricBill", "security", "currentMonth" };
		model.addAttribute("fileName", "myrecord");
		model.addAttribute("headerName", headerName);
		model.addAttribute("headerNameCSV", headerNameCSV);
		model.addAttribute("fields", fields);
		model.addAttribute("sheetName", "Rent-Electricity Bill Details");
		model.addAttribute("data", infoService.getMyRecords(username).getData());
		return new ModelAndView("excelView");
	}

	@RequestMapping(value = "/rd/download", method = RequestMethod.GET)
	public ModelAndView rentDetails(Model model,@RequestParam(required = true) Long id) {
		String headerName[] = { "Id", "Rent", "Electricity Paid", "Electricity Bill", "Security", "Month" };
		String headerNameCSV[] = { "Id", "Rent", "ElecBillPaid", "ElectricBill", "Security", "CurrentMonth" };
		String fields[] = { "id", "rent", "elecBillPaid", "electricBill", "security", "currentMonth" };
		model.addAttribute("fileName", "rent-details");
		model.addAttribute("headerName", headerName);
		model.addAttribute("headerNameCSV", headerNameCSV);
		model.addAttribute("fields", fields);
		model.addAttribute("sheetName", "Rent Details");
		model.addAttribute("data", infoService.getRentDetail(id).getData());
		return new ModelAndView("excelView");
	}

}
