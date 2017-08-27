package com.zongmu.label.dbox.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

	private final static Logger LOGGER = LoggerFactory.getLogger(AuthenticationEntryPointImpl.class);

	private WebConfig webConfig;

	public AuthenticationEntryPointImpl(WebConfig webConfig) {
		this.webConfig = webConfig;
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		LOGGER.warn("Request {} not authenticated.", httpRequest.getRequestURI());

		if (httpRequest.getHeader("Accept") != null && httpRequest.getHeader("Accept").contains("json")) {
			httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		httpResponse.sendRedirect(this.webConfig.getLoginUrl());
	}

}
