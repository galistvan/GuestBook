package hu.igal.sample.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {
	private static final String SPRING_MAIL_HOST = "spring.mail.host";
	private static final String SPRING_MAIL_PORT = "spring.mail.port";

	private static final String SPRING_MAIL_USERNAME = "spring.mail.username";
	private static final String SPRING_MAIL_PASSWORD = "spring.mail.password";

	private static final String SPRING_MAIL_PROTOCOL = "spring.mail.protocol";
	private static final String SPRING_MAIL_SMTP_AUTH = "spring.mail.smtp.auth";
	private static final String SPRING_MAIL_SMTP_STARTTLS_ENABLE = "spring.mail.smtp.starttls.enable";
	private static final String SPRING_MAIL_DEBUG = "spring.mail.debug";

	@Autowired
	private Environment env;

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(env.getProperty(SPRING_MAIL_HOST));
		mailSender.setPort(Integer.parseInt(env.getProperty(SPRING_MAIL_PORT)));

		mailSender.setUsername(env.getProperty(SPRING_MAIL_USERNAME));
		mailSender.setPassword(env.getProperty(SPRING_MAIL_PASSWORD));

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", env.getProperty(SPRING_MAIL_PROTOCOL));
		props.put("mail.smtp.auth", env.getProperty(SPRING_MAIL_SMTP_AUTH));
		props.put("mail.smtp.starttls.enable", env.getProperty(SPRING_MAIL_SMTP_STARTTLS_ENABLE));
		props.put("mail.debug", env.getProperty(SPRING_MAIL_DEBUG));

		return mailSender;
	}
	

}
