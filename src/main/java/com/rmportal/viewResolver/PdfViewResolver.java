package com.rmportal.viewResolver;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import com.rmportal.view.PdfView;

public class PdfViewResolver implements ViewResolver{

	@Override
	public View resolveViewName(String arg0, Locale arg1) throws Exception {
		return new PdfView();
	}

}
