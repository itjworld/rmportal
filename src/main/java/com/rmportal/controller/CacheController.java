package com.rmportal.controller;

import static com.rmportal.util.Utility.getDateAsString;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.rmportal.service.CacheDataService;
import com.rmportal.vo.CacheDetails;

@Controller
public class CacheController {

	@Autowired
	private CacheDataService cacheDataService;

	@Autowired
	private Environment environment;

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/clearstatics")
	public ResponseEntity<Void> clearstatics() {
		System.out.println("inside method clearstatics()");
		cacheDataService.clearstatics();
		return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, "getstatics").build();
	}

	@RequestMapping(value = "/clearstatics2")
	public RedirectView clearstatics2() {
		System.out.println("inside method clearstatics2()");
		cacheDataService.clearstatics();
		return new RedirectView("getstatics");
	}

	@RequestMapping(value = "/getstatics")
	@ResponseBody
	public ResponseEntity<List<CacheDetails>> getEhCacheStat(Model model) {
		System.out.println("inside method getEhCacheStat()");
		return new ResponseEntity<List<CacheDetails>>(cacheDataService.getEhCacheDetail(), HttpStatus.OK);
	}

	@RequestMapping(value = "/download/log", method = RequestMethod.GET)
	public void logger(HttpServletResponse response) throws IOException {
		File file = new File(environment.getRequiredProperty("logging.file"));
		if (!file.exists()) {
			String errorMessage = "Sorry. The file you are looking for does not exist";
			System.out.println(errorMessage);
			OutputStream outputStream = response.getOutputStream();
			outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
			outputStream.close();
			return;
		}

		String mimeType = URLConnection.guessContentTypeFromName(file.getName());
		if (mimeType == null) {
			System.out.println("mimetype is not detectable, will take default");
			mimeType = "application/octet-stream";
		}
		System.out.println("mimetype : " + mimeType);

		response.setContentType(mimeType);
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
		response.setContentLength((int) file.length());
		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
		FileCopyUtils.copy(inputStream, response.getOutputStream());
		response.getOutputStream().close();
		response.getOutputStream().flush();
	}

	@RequestMapping(value = "/download/bk", method = RequestMethod.GET)
	public void backup(HttpServletResponse response) {
		LOGGER.debug("inside method backup");
		InputStream in = null;
		try {
			File file = new File("rmportal_bk_" + getDateAsString(new Date(), "ddMMyyyyHHmm") + ".sql");
			Runtime runtime = Runtime.getRuntime();
			Process p = runtime.exec("mysqldump -u" + "root" + " -p" + "root" + " -B rmportal");
			in = p.getInputStream();
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType == null) {
				LOGGER.debug("mimetype is not detectable, will take default");
				mimeType = "application/octet-stream";
			}
			LOGGER.debug("mimetype : {}", mimeType);
			mimeType = "application/octet-stream";
			response.setContentType(mimeType);
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
			// response.setContentLength((int)file.length());
			FileCopyUtils.copy(in, response.getOutputStream());
			response.getOutputStream().close();
			response.getOutputStream().flush();
		} catch (Exception ex) {
			LOGGER.error("exception generating while taking backup of database : {}", ex);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
