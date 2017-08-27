package com.zongmu.label.dbox.login.service;

import com.zongmu.label.dbox.error.BusinessException;
import com.zongmu.label.dbox.login.controller.RegisterParam;

public interface LoginService {

	void login(String email, String password) throws BusinessException;

	void register(RegisterParam registerParam) throws BusinessException;
}
