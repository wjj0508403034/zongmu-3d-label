package com.zongmu.label.dbox.video.attr.service.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.zongmu.label.dbox.core.entity.BusinessService;
import com.zongmu.label.dbox.video.attr.VideoAttribute;
import com.zongmu.label.dbox.video.attr.repository.VideoAttributeRepo;
import com.zongmu.label.dbox.video.attr.service.VideoAttributeService;

public class VideoAttributeServiceImpl extends BusinessService implements VideoAttributeService {

	public VideoAttributeServiceImpl(ApplicationContext applicationContext) {
		super(applicationContext);

	}

	@Override
	public Page<VideoAttribute> getAttrs(Pageable pageable) {
		return this.getBean(VideoAttributeRepo.class).findAll(pageable);
	}

	@Transactional
	@Override
	public void createAttr(String name) {
		VideoAttribute attr = new VideoAttribute();
		attr.setName(name);
		this.getBean(VideoAttributeRepo.class).save(attr);
	}

}
