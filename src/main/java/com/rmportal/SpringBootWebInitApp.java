package com.rmportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@Import({JpaConfiguration.class,ViewResolverConfig.class,SecurityConfig.class})
@SpringBootApplication(scanBasePackages = { "com.rmportal" }) // same
																// as
																// @Configuration
																// @EnableAutoConfiguration
																// @ComponentScan
public class SpringBootWebInitApp extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebInitApp.class, args);
	}
	
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootWebInitApp.class);
    }
}
