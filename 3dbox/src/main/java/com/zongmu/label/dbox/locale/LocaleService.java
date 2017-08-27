package com.zongmu.label.dbox.locale;

import org.springframework.context.MessageSource;

public interface LocaleService {
	String getMessage(String key);

	String getSubject(String key);

	String getErrorMessage(String errorCode);

	MessageSource getMessageSource();
}
