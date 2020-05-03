package com.rmportal.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URL;
import java.net.URLConnection;
import java.time.ZoneId;
import java.util.Date;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmportal.vo.ResponseMessage;

public class Utility {
	public static ResponseMessage updateResponse(String message, boolean status) {
		return new ResponseMessage(message, status);
	}

	public static String getDateAsString(Date date, String pattern) {
		return DateFormatUtils.format(date, pattern);
	}

	public static boolean isValidJson(String json) {
		return (StringUtils.isNotEmpty(json) && (json.startsWith("{") || json.startsWith("["))) ? true : false;
	}

	public static String toCamelCase(String inputString) {
		if (StringUtils.equalsIgnoreCase("mew", inputString))
			return firstLastLetterUpperCase(inputString);
		else
			return WordUtils.capitalizeFully(inputString);
	}

	private static String firstLastLetterUpperCase(String inputString) {
		if (StringUtils.isBlank(inputString))
			return null;
		char firstChar = inputString.charAt(0);
		char lastChar = inputString.charAt(inputString.length() - 1);
		String midChars = StringUtils.mid(inputString, 1, inputString.length() - 2);
		String midCharsToLowerCase = StringUtils.lowerCase(midChars);
		char firstCharToUpperCase = Character.toUpperCase(firstChar);
		char lastCharToUpperCase = Character.toUpperCase(lastChar);
		return firstCharToUpperCase + midCharsToLowerCase + lastCharToUpperCase;
	}

	public static String getURLSource(String url) throws IOException {
		CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
		URL urlObject = new URL(url);
		URLConnection urlConnection = urlObject.openConnection();
		String htmlSource = converStreamToString(urlConnection.getInputStream());
		return htmlSource;
	}

	public static String converStreamToString(InputStream inputStream) throws IOException {
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
			return buffer.lines().collect(Collectors.joining("\n"));
		}
	}

	public static Date addDays(Date existingDate, int days) {
		return Date.from((existingDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()).plusDays(days)
				.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static Date addMinutes(Date date, int plusMinutes) {
		date = Date.from((date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()).plusMinutes(plusMinutes)
				.atZone(ZoneId.systemDefault()).toInstant());
		return date;
	}

	public static <T> T parseJSONToObject(String result, Class<T> clazz)
			throws IOException, JsonParseException, JsonMappingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(result, clazz);
	}
}
