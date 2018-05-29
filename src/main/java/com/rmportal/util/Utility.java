package com.rmportal.util;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.rmportal.vo.ResponseMessage;

public class Utility {
	public static ResponseMessage updateResponse(String message, boolean status) {
		return new ResponseMessage(message, status);
	}

	public static String getDateAsString(Date date, String pattern) {
		return DateFormatUtils.format(date, pattern);
	}
}
