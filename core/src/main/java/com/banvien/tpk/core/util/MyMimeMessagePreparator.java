package com.banvien.tpk.core.util;


import java.io.Serializable;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

public class MyMimeMessagePreparator implements MimeMessagePreparator, Serializable {
	/**
	 * 
	 */
	private String[] bccRecipients;
	private String[] ccRecipients;
	private String[] recipients;
	private String sender;
	private String subject;
	private String text;
	private String defaultFrom;
	
	 public String[] getBccRecipients() {
		return bccRecipients;
	}

	public void setBccRecipients(String[] bccRecipients) {
		this.bccRecipients = bccRecipients;
	}
	
	public String[] getRecipients() {
		return recipients;
	}

	public void setRecipients(String[] recipients) {
		this.recipients = recipients;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getDefaultFrom() {
		return defaultFrom;
	}

	public void setDefaultFrom(String defaultFrom) {
		this.defaultFrom = defaultFrom;
	}
	
	public String[] getCcRecipients() {
		return ccRecipients;
	}

	public void setCcRecipients(String[] ccRecipients) {
		this.ccRecipients = ccRecipients;
	}

	public MyMimeMessagePreparator(String sender,
			String subject, String defaultFrom, String text) {
		this.sender = sender;
		this.subject = subject;
		this.defaultFrom = defaultFrom;
		this.text = text;
	}

	public void prepare(MimeMessage mimeMessage) throws Exception {		
         MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
         if(recipients != null && recipients.length > 0) {
        	 message.setTo(recipients);
         }
         if(bccRecipients != null && bccRecipients.length > 0) {
        	 message.setBcc(bccRecipients);
         }
         if(ccRecipients != null && ccRecipients.length > 0) {
        	 message.setCc(ccRecipients);
         }
         if(sender != null) {
         	message.setFrom(sender);
         }else if(defaultFrom != null) {
         	message.setFrom(defaultFrom);
         }
         message.setSubject(subject);
         message.setText(text, true);
      }
}
