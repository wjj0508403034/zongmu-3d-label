package com.zongmu.label.dbox.email;

import com.zongmu.label.dbox.error.BusinessException;

public interface EmailService {

	void send(String to, EmailTemplate template) throws BusinessException;
}
