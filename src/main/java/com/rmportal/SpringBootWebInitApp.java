package com.rmportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Import({ JpaConfiguration.class, ViewResolverConfig.class, SecurityConfig.class })
@SpringBootApplication(scanBasePackages = { "com.rmportal" }) /*
																 * same
																 * as @Configuration @EnableAutoConfigurationSFS @ComponentScan
																 */
public class SpringBootWebInitApp extends SpringBootServletInitializer {

	public static void main(String[] args) {
		
		SpringApplication.run(SpringBootWebInitApp.class, args);
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootWebInitApp.class);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*")
						.allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin",
								"Access-Control-Request-Method", "Access-Control-Request-Headers", "x-www-form-rm-call",
								"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
						.exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials",
								"access-control-expose-headers")
						.allowedMethods("PUT", "DELETE", "GET", "POST", "OPTIONS").allowCredentials(false).maxAge(3600);
			}
		};
	}

}
