package com.zongmu.label.dbox.login;

import org.springframework.security.core.authority.AuthorityUtils;

import com.zongmu.label.dbox.user.User;

public class UserInfo extends org.springframework.security.core.userdetails.User {
	private static final long serialVersionUID = -2421033024714502747L;
	private User user;

	public UserInfo(User user) {
		super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
		this.setUser(user);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}