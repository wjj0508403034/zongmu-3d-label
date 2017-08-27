package com.zongmu.label.dbox.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zongmu.label.dbox.locale.LocaleService;
import com.zongmu.label.dbox.locale.impl.LocaleServiceImpl;
import com.zongmu.label.dbox.login.service.LoginService;
import com.zongmu.label.dbox.login.service.impl.LoginServiceImpl;
import com.zongmu.label.dbox.task.review.service.ReasonService;
import com.zongmu.label.dbox.task.review.service.impl.ReasonServiceImpl;
import com.zongmu.label.dbox.user.service.UserService;
import com.zongmu.label.dbox.user.service.impl.UserServiceImpl;
import com.zongmu.label.dbox.video.attr.service.VideoAttributeService;
import com.zongmu.label.dbox.video.attr.service.impl.VideoAttributeServiceImpl;

@Configuration
public class AutoBusinessConfiguration {

	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	public LocaleService localeService() {
		return new LocaleServiceImpl(this.applicationContext);
	}

	@Bean
	public LoginService loginService() {
		return new LoginServiceImpl(this.applicationContext);
	}

	@Bean
	public UserService userService() {
		return new UserServiceImpl(this.applicationContext);
	}

	@Bean
	public VideoAttributeService videoAttributeService() {
		return new VideoAttributeServiceImpl(this.applicationContext);
	}

	@Bean
	public ReasonService reasonService() {
		return new ReasonServiceImpl(this.applicationContext);
	}
}
