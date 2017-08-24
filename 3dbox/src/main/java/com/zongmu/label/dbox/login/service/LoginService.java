package com.zongmu.label.dbox.login.service;

import com.zongmu.label.dbox.error.BusinessException;

public interface LoginService {

	void login(String email, String password) throws BusinessException;
}
