package com.zongmu.label.dbox.web;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "web")
@PropertySource(value = "classpath:/META-INF/web.properties")
public class WebConfig {

	private String[] anonymousRequests;
	private String loginUrl;

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String[] getAnonymousRequests() {
		return anonymousRequests;
	}

	public void setAnonymousRequests(String[] anonymousRequests) {
		this.anonymousRequests = anonymousRequests;
	}
}
