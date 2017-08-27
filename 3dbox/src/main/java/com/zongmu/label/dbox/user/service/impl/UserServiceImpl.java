package com.zongmu.label.dbox.user.service.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zongmu.label.dbox.core.entity.BusinessService;
import com.zongmu.label.dbox.user.User;
import com.zongmu.label.dbox.user.repository.UserRepo;
import com.zongmu.label.dbox.user.service.UserService;

public class UserServiceImpl extends BusinessService implements UserService {

	public UserServiceImpl(ApplicationContext applicationContext) {
		super(applicationContext);
	}

	@Override
	public Page<User> findUsers(Pageable pageable) {
		return this.getBean(UserRepo.class).findAll(pageable);
	}

	@Override
	public User findByEmail(String username) {
		return this.getBean(UserRepo.class).findByEmail(username);
	}

}
