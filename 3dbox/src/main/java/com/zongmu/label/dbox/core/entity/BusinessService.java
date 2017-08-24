package com.zongmu.label.dbox.core.entity;

import org.springframework.context.ApplicationContext;

public abstract class BusinessService {

	private ApplicationContext applicationContext;

	public BusinessService(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	protected <T> T getBean(Class<T> requiredType){
		return this.applicationContext.getBean(requiredType);
	}
}
