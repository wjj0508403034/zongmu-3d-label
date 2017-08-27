package com.zongmu.label.dbox.login.service.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.zongmu.label.dbox.core.entity.BusinessService;
import com.zongmu.label.dbox.login.UserInfo;
import com.zongmu.label.dbox.user.User;
import com.zongmu.label.dbox.user.service.UserService;

public class UserDetailsServiceImpl extends BusinessService implements UserDetailsService {

	private UserService userService;

	public UserDetailsServiceImpl(ApplicationContext applicationContext) {
		super(applicationContext);
		this.userService = this.getBean(UserService.class);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("User with email=%s was not found", username));
		}
		return new UserInfo(user);
	}
}
