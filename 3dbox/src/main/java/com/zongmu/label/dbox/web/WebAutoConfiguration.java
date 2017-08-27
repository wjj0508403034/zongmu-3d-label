package com.zongmu.label.dbox.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
public class WebAutoConfiguration {

	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint(WebConfig webConfig) {
		return new AuthenticationEntryPointImpl(webConfig);
	}
}
