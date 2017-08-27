package com.zongmu.label.dbox.locale.impl;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;

import com.zongmu.label.dbox.core.entity.BusinessService;
import com.zongmu.label.dbox.locale.LocaleService;

public class LocaleServiceImpl extends BusinessService implements LocaleService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LocaleServiceImpl.class);

	private MessageSource messageSource;

	public LocaleServiceImpl(ApplicationContext applicationContext) {
		super(applicationContext);
		this.messageSource = this.getBean(MessageSource.class);
	}

	@Override
	public String getMessage(String key) {
		try {
			return this.messageSource.getMessage(key, null, Locale.CHINA);
		} catch (Exception ex) {
			LOGGER.warn("Not found {} string in localization file", key);
		}
		return null;
	}

	@Override
	public MessageSource getMessageSource() {
		return this.messageSource;
	}

	@Override
	public String getErrorMessage(String errorCode) {
		return this.getMessage("ErrorCode_" + errorCode);
	}

	@Override
	public String getSubject(String key) {
		return this.getMessage("Mail_Subject_" + key);
	}
}
