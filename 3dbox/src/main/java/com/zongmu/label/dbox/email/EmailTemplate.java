package com.zongmu.label.dbox.email;

import org.thymeleaf.TemplateEngine;

import com.zongmu.label.dbox.error.BusinessException;
import com.zongmu.label.dbox.locale.LocaleService;

public interface EmailTemplate {

	String getHtml(TemplateEngine templateEngine) throws BusinessException;

	void setVariable(String name, Object value);

	String getSubject(LocaleService localeService);
}
