package com.rmportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rmportal.service.InfoService;

@Controller
@RequestMapping(value = "/api/v1")
public class DownloadController {

	@Autowired
	private InfoService infoService;

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ModelAndView download(Model model) {
		// model.addAttribute("roomDetails", infoService.getRecords());
		return new ModelAndView("excelView", "roomDetails", infoService.getRecords());
	}
}
