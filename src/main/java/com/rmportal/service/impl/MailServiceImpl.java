package com.rmportal.service.impl;

import java.io.ByteArrayOutputStream;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.rmportal.service.InfoService;
import com.rmportal.service.MailService;
import com.rmportal.view.PdfView;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private PdfView pdfView;

	@Autowired
	private InfoService infoService;

	@Override
	public String sendEmail(String recepients, String subject, String cc, String message) {

		MimeMessagePreparator preparator = getContentWithAttachementMessagePreparator(recepients, subject, cc, message);

		try {
			mailSender.send(preparator);
			System.out.println("Message With Attachement has been sent.............................");
			// preparator =
			// getContentAsInlineResourceMessagePreparator(recepients);
			// mailSender.send(preparator);
			// System.out.println("Message With Inline Resource has been
			// sent.........................");
			return "{\"message\": \"OK\"}";
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
			return "{\"message\": \"ERROR\"}";
		}
	}

	private MimeMessagePreparator getContentWithAttachementMessagePreparator(final String recepients, String subject,
			String cc, String message) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
				helper.setSubject(subject);
				helper.setFrom("alert@gmail.com");
				setTo(recepients, helper);
				if(StringUtils.isNotEmpty(cc))
					setCc(recepients, cc, helper);
				helper.setText(message);
				Document document = new Document(PageSize.A4.rotate(), 36, 36, 54, 36);
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				PdfWriter.getInstance(document, outputStream);
				document.open();
				//pdfView.getPDFDocument(document, infoService.getRecords());
				document.close();
				byte[] bytes = outputStream.toByteArray();
				// construct the pdf body part
				DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
				helper.addAttachment("records.pdf", dataSource);
				outputStream.close();
			}

			private void setCc(final String recepients, String cc, MimeMessageHelper helper) throws MessagingException {
				if (cc.contains(","))
					helper.setCc(cc.split(","));
				else if (recepients.contains(";"))
					helper.setCc(cc.split(";"));
				else
					helper.setCc(cc);
			}

			private void setTo(final String recepients, MimeMessageHelper helper) throws MessagingException {
				if (recepients.contains(","))
					helper.setTo(recepients.split(","));
				else if (recepients.contains(";"))
					helper.setTo(recepients.split(";"));
				else
					helper.setTo(recepients);
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
		message.setTo(receipients.split(","));
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
