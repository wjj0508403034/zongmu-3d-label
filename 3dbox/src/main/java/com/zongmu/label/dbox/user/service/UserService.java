package com.zongmu.label.dbox.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zongmu.label.dbox.user.User;

public interface UserService {

	Page<User> findUsers(Pageable pageable);

	User findByEmail(String username);

}
