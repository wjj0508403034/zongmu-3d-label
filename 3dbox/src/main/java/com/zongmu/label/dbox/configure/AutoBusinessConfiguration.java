package com.zongmu.label.dbox.configure;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zongmu.label.dbox.login.service.LoginService;
import com.zongmu.label.dbox.login.service.impl.LoginServiceImpl;
import com.zongmu.label.dbox.user.service.UserService;
import com.zongmu.label.dbox.user.service.impl.UserServiceImpl;

@Configuration
public class AutoBusinessConfiguration {

	private ApplicationContext applicationContext;
	
	@Bean
	public LoginService loginService(){
		return new LoginServiceImpl(this.applicationContext);
	}
	
	@Bean
	public UserService userService(){
		return new UserServiceImpl(this.applicationContext);
	}
}
