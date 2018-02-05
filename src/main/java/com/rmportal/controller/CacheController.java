package com.rmportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rmportal.service.CacheDataService;
import com.rmportal.vo.CacheDetails;

@Controller
public class CacheController {

	@Autowired
	private CacheDataService cacheDataService;

	@RequestMapping(value = "/clearstatics")
	public String clearstatics() {
		System.out.println("inside method clearstatics()");
		cacheDataService.clearstatics();
		return "redirect:/getstatics";
	}

	@RequestMapping(value = "/getstatics")
	@ResponseBody
	public ResponseEntity<List<CacheDetails>> getEhCacheStat(Model model) {
		System.out.println("inside method getEhCacheStat()");
		return new ResponseEntity<List<CacheDetails>>(cacheDataService.getEhCacheDetail(), HttpStatus.OK);
	}

}
