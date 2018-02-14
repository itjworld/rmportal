package com.rmportal.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.List;

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
import com.rmportal.service.MailService;
import com.rmportal.view.PdfView;
import com.rmportal.vo.MailDetails;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private PdfView pdfView;

	@Override
	public String sendEmail(MailDetails mail) {

		MimeMessagePreparator preparator = getContentWithAttachementMessagePreparator(mail.getTo(), mail.getSubject(), mail.getCc(), mail.getMessage());

		try {
			mailSender.send(preparator);
			System.out.println("Message With Attachement has been sent.............................");
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
	
	private byte[] getPDFBytes(List<?> data, String[] headerName, String[] fields) throws Exception {
		byte[] bytes;
		Document document = new Document(PageSize.A4.rotate(), 36, 36, 54, 36);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, outputStream);
		document.open();
		pdfView.getPDFDocument(document, data,headerName,fields);
		document.close();
		bytes= outputStream.toByteArray();
		outputStream.close();
		return bytes;
	}
	private void addAttachment(final String fileName,final byte[] bytes,final String type,final MimeMessageHelper helper) throws MessagingException {
		DataSource dataSource = new ByteArrayDataSource(bytes, type);
		helper.addAttachment(fileName, dataSource);
	}
	private MimeMessageHelper createMailMessage(final String from,final String to, final String cc,final String bcc, final String subject,final String body,final MimeMessage message) throws MessagingException {
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(from);
		helper.setTo(setInternetAddress(to));
		if(StringUtils.isNotEmpty(cc)) {
			helper.setCc(setInternetAddress(cc));
		}
		if(StringUtils.isNotEmpty(bcc)) {
			helper.setBcc(setInternetAddress(bcc));
		}
		helper.setText(body);
		return helper;
	}
	
	private String[] setInternetAddress(final String recepients) {
		if (recepients.contains(";")) {
			return recepients.split(";");
		}
		return recepients.split(",");
	}

	@SuppressWarnings("unused")
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

	public String triggerEmail(MailDetails mail) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setText(mail.getMessage());
		message.setTo(mail.getTo().split(","));
		message.setSubject(mail.getSubject());
		message.setFrom("test@gmail.com");
		try {
			mailSender.send(message);
			return "{\"message\": \"OK\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"message\": \"Error\"}";
		}
	}

	@Override
	public String sendEmail(MailDetails mail, List<?> data, String[] headerName, String[] fields,final String fileName) {
		MimeMessage message=null;
		MimeMessageHelper helper=null;
		try {
			message= mailSender.createMimeMessage();
			helper=createMailMessage("alert@gmail.com", mail.getTo(), mail.getCc(), null, mail.getSubject(), mail.getMessage(), message);
			addAttachment(fileName, getPDFBytes(data, headerName, fields), "application/pdf", helper);
			mailSender.send(message);
			System.out.println("Message With Attachement has been sent.............................");
			return "{\"message\": \"OK\"}";
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			return "{\"message\": \"ERROR\"}";
		}finally {
			helper=null;
			message=null;
		}
	}

	
	
}
