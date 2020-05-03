package com.rmportal;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource("classpath:smtp.properties")
public class MailConfig {

	@Value("${spring.mail.host}")
	private String host;
	@Value("${spring.mail.port}")
	private Integer port;
	@Value("${spring.mail.username}")
	private String username;
	@Value("${spring.mail.password}")
	private String password;
	@Value("${spring.mail.protocol}")
	private String protocol;
	@Value("${spring.mail.smtp.auth}")
	private String auth;
	@Value("${spring.mail.smtp.starttls.enable}")
	private String starttls;
	
	@Bean
	public JavaMailSender getMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		mailSender.setHost(host);
		mailSender.setPort(port);
		mailSender.setUsername(username);
		mailSender.setPassword(password);

		Properties javaMailProperties = new Properties();
//		javaMailProperties.put("mail.smtp.starttls.enable", starttls);
		javaMailProperties.put("mail.smtp.auth", auth);
		javaMailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		javaMailProperties.put("mail.smtp.socketFactory.port", port);
		mailSender.setJavaMailProperties(javaMailProperties);
		return mailSender;
	}
	
}
