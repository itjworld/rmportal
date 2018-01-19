package com.rmportal.util;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@Component
public class MailSender implements MailService {

	@Autowired
	JavaMailSender mailSender;

	@Override
	public void sendEmail(String recepients, String subject, String cc, String message) {

		MimeMessagePreparator preparator = getContentWtihAttachementMessagePreparator(recepients);

		try {
			mailSender.send(preparator);
			System.out.println("Message With Attachement has been sent.............................");
			preparator = getContentAsInlineResourceMessagePreparator(recepients);
			mailSender.send(preparator);
			System.out.println("Message With Inline Resource has been sent.........................");
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
		}
	}

	private MimeMessagePreparator getContentWtihAttachementMessagePreparator(final String recepients) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

				helper.setSubject("Your order on Demoapp with attachement");
				helper.setFrom("customerserivces@yourshop.com");
				helper.setTo(recepients);
				String content = "Hi, Please find attachement for all records.";

				helper.setText(content);

				// Add a resource as an attachment
				helper.addAttachment("cutie.png", new ClassPathResource("linux-icon.png"));
			}
		};
		return preparator;
	}

	private MimeMessagePreparator getContentAsInlineResourceMessagePreparator(final String recepients) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

				helper.setSubject("Your order on Demoapp with Inline resource");
				helper.setFrom("customerserivces@yourshop.com");
				helper.setTo(recepients);

				String content = "Hi, Please find attachement for all records.";

				// Add an inline resource.
				// use the true flag to indicate you need a multipart message
				helper.setText("<html><body><p>" + content + "</p><img src=''></body></html>", true);
				// helper.addInline("company-logo", new
				// ClassPathResource("linux-icon.png"));
			}
		};
		return preparator;
	}

	public String triggerEmail(String content, String receipients, String cc, String subject) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setText(content);
		message.setTo(receipients);
		message.setSubject(subject);
		message.setFrom("test@gmail.com");
		try {
			mailSender.send(message);
			return "{\"message\": \"OK\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"message\": \"Error\"}";
		}
	}

}
