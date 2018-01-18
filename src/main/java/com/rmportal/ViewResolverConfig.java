package com.rmportal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import com.rmportal.viewResolver.CsvViewResolver;
import com.rmportal.viewResolver.ExcelViewResolver;
import com.rmportal.viewResolver.PdfViewResolver;

@Configuration
public class ViewResolverConfig {
	
	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
	    ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
	    resolver.setContentNegotiationManager(manager);

	    // Define all possible view resolvers
	    final List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
	    resolvers.add(csvViewResolver());
	    resolvers.add(excelViewResolver());
	    resolvers.add(pdfViewResolver());

	    resolver.setViewResolvers(resolvers);
	    return resolver;
	}
	
	@Bean
	public ViewResolver excelViewResolver() {
	    return new ExcelViewResolver();
	}

	/*
	 * Configure View resolver to provide Csv output using Super Csv library to
	 * generate Csv output for an object content
	 */
	@Bean
	public ViewResolver csvViewResolver() {
	    return new CsvViewResolver();
	}

	/*
	 * Configure View resolver to provide Pdf output using iText library to
	 * generate pdf output for an object content
	 */
	@Bean
	public ViewResolver pdfViewResolver() {
	    return new PdfViewResolver();
	}

}
