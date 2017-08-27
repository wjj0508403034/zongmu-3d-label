package com.zongmu.label.dbox.email.impl;

import java.util.Date;
import java.util.Properties;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.EmailConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.zongmu.label.dbox.core.entity.BusinessService;
import com.zongmu.label.dbox.email.EmailService;
import com.zongmu.label.dbox.email.EmailTemplate;
import com.zongmu.label.dbox.email.Smtp;
import com.zongmu.label.dbox.error.BusinessException;
import com.zongmu.label.dbox.error.ErrorCodes;
import com.zongmu.label.dbox.locale.LocaleService;

public class EmailServiceImpl extends BusinessService implements EmailService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

	private final static int SOCKET_TIMEOUT_MS = 10000;
	private final static int MAIL_SMTP_TIMEOUT = 20000;

	private Smtp smtp;
	private SpringTemplateEngine templateEngine;
	private JavaMailSenderImpl mailSender;
	private LocaleService localeService;

	public EmailServiceImpl(ApplicationContext applicationContext) {
		super(applicationContext);
		this.smtp = this.getBean(Smtp.class);
		this.templateEngine = this.getBean(SpringTemplateEngine.class);
		this.localeService = this.getBean(LocaleService.class);
	}

	@Override
	public void send(String to, EmailTemplate template) throws BusinessException {
		try {
			LOGGER.info("Start to sending mail ...");
			JavaMailSenderImpl sender = this.getMailSender();
			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, EmailConstants.UTF_8);
			helper.setSentDate(new Date());
			InternetAddress from = new InternetAddress(this.smtp.getFrom());
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(template.getSubject(this.localeService));
			helper.setText(template.getHtml(this.templateEngine), true);
			sender.send(message);
			LOGGER.info("Send mail successfully.");
		} catch (Exception ex) {
			LOGGER.info("Send mail failed.", ex);
			throw new BusinessException(ErrorCodes.Send_Mail_Failed);
		}
	}

	private JavaMailSenderImpl getMailSender() {
		if (this.mailSender == null) {
			this.mailSender = new JavaMailSenderImpl();
			this.mailSender.setHost(smtp.getServer());
			this.mailSender.setPort(smtp.getPort());
			this.mailSender.setDefaultEncoding(EmailConstants.UTF_8);
			if (!StringUtils.isEmpty(smtp.getUserName()) && !StringUtils.isEmpty(smtp.getPassword())) {
				this.mailSender.setUsername(smtp.getUserName());
				this.mailSender.setPassword(smtp.getPassword());
			}

			Properties properties = new Properties();
			properties.setProperty(EmailConstants.MAIL_DEBUG, "true");
			properties.setProperty(EmailConstants.MAIL_SMTP_TIMEOUT, Integer.toString(MAIL_SMTP_TIMEOUT));
			properties.setProperty(EmailConstants.MAIL_SMTP_CONNECTIONTIMEOUT, Integer.toString(SOCKET_TIMEOUT_MS));

			this.mailSender.setJavaMailProperties(properties);
		}

		return this.mailSender;
	}

}
