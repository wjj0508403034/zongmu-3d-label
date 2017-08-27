package com.zongmu.label.dbox.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String root() {
		return "redirect:/index.html";
	}

	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/register.html", method = RequestMethod.GET)
	public String register() {
		return "register";
	}

	@RequestMapping(value = "/forget-password.html", method = RequestMethod.GET)
	public String forgetPassword() {
		return "forget-password";
	}

	@RequestMapping(value = "/reset-password.html", method = RequestMethod.GET)
	public String resetPassword() {
		return "reset-password";
	}
}
