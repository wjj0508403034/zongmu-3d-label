package com.zongmu.label.dbox.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring4.SpringTemplateEngine;
import com.zongmu.label.dbox.email.impl.EmailServiceImpl;

@Configuration
public class EmailAutoConfiguration {

	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	@ConfigurationProperties(prefix = "smtp")
	public Smtp smtp() {
		return new Smtp();
	}

	@ConditionalOnBean({ Smtp.class, SpringTemplateEngine.class })
	@Bean
	public EmailService emailService() {
		return new EmailServiceImpl(this.applicationContext);
	}
}
