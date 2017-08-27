package com.zongmu.label.dbox.email.impl;

import java.util.Locale;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.zongmu.label.dbox.email.EmailTemplate;
import com.zongmu.label.dbox.error.BusinessException;
import com.zongmu.label.dbox.error.ErrorCodes;
import com.zongmu.label.dbox.locale.LocaleService;

public class EmailTemplateImpl implements EmailTemplate {

	private Context context = new Context(Locale.CHINA);
	private String emailName;

	public EmailTemplateImpl(String emailName) {
		this.emailName = emailName;
	}

	@Override
	public String getHtml(TemplateEngine templateEngine) throws BusinessException {
		try {
			return templateEngine.process(this.emailName, context);
		} catch (Exception ex) {
			throw new BusinessException(ErrorCodes.Parse_Email_Template_Failed);
		}
	}

	@Override
	public String getSubject(LocaleService localeService) {
		return localeService.getMessage("Mail_Subject_" + this.emailName);
	}

	@Override
	public void setVariable(String name, Object value) {
		this.context.setVariable(name, value);
	}

}
