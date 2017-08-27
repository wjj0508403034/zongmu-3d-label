package com.zongmu.label.dbox.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zongmu.label.dbox.error.BusinessException;
import com.zongmu.label.dbox.login.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public void login(@RequestBody LoginParam loginParam) throws BusinessException {
		this.loginService.login(loginParam.getEmail(), loginParam.getPassword());
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public void register(@RequestBody RegisterParam registerParam) throws BusinessException {
		//this.loginService.register(loginParam.getEmail(), loginParam.getPassword());
	}
}
