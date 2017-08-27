package com.zongmu.label.dbox.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private WebConfig webConfig;

	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().httpBasic().authenticationEntryPoint(authenticationEntryPoint);
		http.authorizeRequests().antMatchers(this.webConfig.getAnonymousRequests()).permitAll();
		http.authorizeRequests().antMatchers("/**").hasAnyRole("USER").anyRequest().authenticated();
	}
}
