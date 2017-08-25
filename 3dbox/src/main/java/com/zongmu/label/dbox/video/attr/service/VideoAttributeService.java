package com.zongmu.label.dbox.video.attr.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zongmu.label.dbox.video.attr.VideoAttribute;

public interface VideoAttributeService {

	Page<VideoAttribute> getAttrs(Pageable pageable);

	void createAttr(String name);

}
