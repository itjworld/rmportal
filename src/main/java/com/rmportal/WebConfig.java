package com.rmportal;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter implements ServletContextInitializer {

	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
			.allowedOrigins("*")
			.allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                    "Access-Control-Request-Headers")
            .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
			.allowedMethods("PUT", "DELETE","GET","POST")
			.allowCredentials(false).maxAge(3600);
	}

	@Override
	public void onStartup(ServletContext arg0) throws ServletException {
		System.err.println("------------------------------------");
	}
}
