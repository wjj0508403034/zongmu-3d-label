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
}
