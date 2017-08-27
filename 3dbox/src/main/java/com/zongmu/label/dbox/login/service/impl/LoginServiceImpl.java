package com.zongmu.label.dbox.login.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import com.zongmu.label.dbox.core.entity.BusinessService;
import com.zongmu.label.dbox.email.EmailService;
import com.zongmu.label.dbox.email.EmailTemplate;
import com.zongmu.label.dbox.email.EmailTemplateNames;
import com.zongmu.label.dbox.email.impl.EmailTemplateImpl;
import com.zongmu.label.dbox.error.BusinessException;
import com.zongmu.label.dbox.error.ErrorCodes;
import com.zongmu.label.dbox.login.controller.RegisterParam;
import com.zongmu.label.dbox.login.service.LoginService;
import com.zongmu.label.dbox.user.User;
import com.zongmu.label.dbox.user.UserStatus;
import com.zongmu.label.dbox.user.repository.UserRepo;

public class LoginServiceImpl extends BusinessService implements LoginService {

	private final static Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);

	public LoginServiceImpl(ApplicationContext applicationContext) {
		super(applicationContext);

	}

	@Transactional(rollbackFor = BusinessException.class)
	@Override
	public void register(RegisterParam registerParam) throws BusinessException {
		if (StringUtils.isEmpty(registerParam.getEmail())) {
			throw new BusinessException(ErrorCodes.Register_Failed_Due_To_Email_Is_Empty);
		}

		if (StringUtils.isEmpty(registerParam.getPassword())) {
			throw new BusinessException(ErrorCodes.Register_Failed_Due_To_Password_Is_Empty);
		}

		if (!StringUtils.equals(registerParam.getPassword(), registerParam.getRepeatPassword())) {
			throw new BusinessException(ErrorCodes.Register_Failed_Due_To_Password_Not_Match);
		}

		boolean exists = this.getBean(UserRepo.class).existsByEmail(registerParam.getEmail());
		if (exists) {
			throw new BusinessException(ErrorCodes.Register_Failed_Due_To_User_Exists);
		}

		User user = new User();
		user.setEmail(registerParam.getEmail());
		user.setPassword(registerParam.getPassword());
		this.getBean(UserRepo.class).save(user);

		this.sendRegisterMail(user);
	}

	@Override
	public void login(String email, String password) throws BusinessException {
		User user = this.getBean(UserRepo.class).findByEmail(email);
		if (user == null) {
			throw new BusinessException(ErrorCodes.Login_Failed_Due_To_User_Not_Exists);
		}

		if (user.getStatus() == UserStatus.UnActive) {
			throw new BusinessException(ErrorCodes.Login_Failed_Due_To_User_Not_Active);
		}

		if (user.getStatus() == UserStatus.Locked) {
			throw new BusinessException(ErrorCodes.Login_Failed_Due_To_User_Locked);
		}

		if (user.getLoginFailedCount() >= 5) {
			if (user.getLockedDate().plusDays(1).isAfter(DateTime.now())) {
				throw new BusinessException(ErrorCodes.Login_Failed_Due_To_Password_Failed_Many_Times);
			}
			user.setLoginFailedCount(0);
		}

		if (!StringUtils.equals(user.getPassword(), password)) {
			user.setLoginFailedCount(user.getLoginFailedCount() + 1);
			if (user.getLoginFailedCount() >= 5) {
				user.setLockedDate(DateTime.now());
			}
			this.getBean(UserRepo.class).save(user);
			throw new BusinessException(ErrorCodes.Login_Failed_Due_To_Password_Invalid);
		}

		user.setLoginFailedCount(0);
		this.getBean(UserRepo.class).save(user);
		this.autoLogin(user);
	}

	private void sendRegisterMail(User user) throws BusinessException {
		EmailTemplate emailTemplate = new EmailTemplateImpl(EmailTemplateNames.Register_Success);
		emailTemplate.setVariable("user", user);
		emailTemplate.setVariable("website", "http://www.baidu.com");
		this.getBean(EmailService.class).send(user.getEmail(), emailTemplate);
	}

	private void autoLogin(User user) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				user.getEmail(), user.getPassword());
		this.getBean(AuthenticationManager.class).authenticate(usernamePasswordAuthenticationToken);
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		LOGGER.info("User {} login successfully", user.getEmail());
	}

}
