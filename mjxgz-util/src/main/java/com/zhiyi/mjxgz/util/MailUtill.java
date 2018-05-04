package com.zhiyi.mjxgz.util;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailUtill {
    private static Logger logger = LoggerFactory.getLogger(MailUtill.class);
	 @Autowired
	 private JavaMailSender mailSender; 
	 @Value("${spring.mail.username}")
	 String from;
	public  String sendEmail(String userEmail,String subject,String text){  
        try  
        {  
            final MimeMessage mimeMessage = this.mailSender.createMimeMessage();  
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);  
            mimeMessage.setContent(text, "text/html;charset = gbk");  
            message.setFrom(from);  
            message.setTo(userEmail);  
            message.setSubject(subject);  
            //message.setText(text);  
            this.mailSender.send(mimeMessage);  
        }  
        catch(Exception ex)  
        {  
        	logger.error("---sendEmail--error:"+ex.getMessage());
        }  
        return  "success";
    }  
}
