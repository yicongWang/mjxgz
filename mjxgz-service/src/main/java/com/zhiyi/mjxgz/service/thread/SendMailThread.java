package com.zhiyi.mjxgz.service.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyi.mjxgz.util.MailUtill;
@Service
public class SendMailThread implements Runnable {
	private Logger logger = LoggerFactory.getLogger(SendMailThread.class);
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	private String userEmail;
	private String subject;
	private String text;
/*	public SendMailThread(){
	}
	public SendMailThread(String userEmail,String subject,String text){
		this.userEmail = userEmail;
		this.subject = subject;
		this.text = text;
	}*/
    @Autowired
	private MailUtill mailUtill; 
	@Override
	public void run() {
		logger.info("---sendEmail---start----");
		try {
			mailUtill.sendEmail(userEmail, subject, text);
		} catch (Exception e) {
			logger.error("----sendEmail--error:"+e.getMessage());
		}
		logger.info("---sendEmail---end----");
	}

}
