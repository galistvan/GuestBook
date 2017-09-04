package hu.igal.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import hu.igal.sample.Quote;

@Service
public class EmailService {
	 
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private Environment env;
    
    public void sendQuoteMessage(Quote quote) {
        SimpleMailMessage message = new SimpleMailMessage(); 
        
        message.setTo(env.getProperty("spring.mail.to")); 
        message.setSubject(env.getProperty("spring.mail.subject")); 
        message.setText("new quote: \n" + quote.toString());
        emailSender.send(message);
    }
}
