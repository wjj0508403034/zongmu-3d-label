package com.zongmu.label.dbox.video.attr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zongmu.label.dbox.error.BusinessException;
import com.zongmu.label.dbox.video.attr.VideoAttribute;
import com.zongmu.label.dbox.video.attr.service.VideoAttributeService;

@Controller
@RequestMapping(value = "/video-attrs")
public class VideoAttributeController {

	@Autowired
	private VideoAttributeService videoAttributeService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Page<VideoAttribute> getAttrs(
			@RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize)
			throws BusinessException {
		Pageable pageable = new PageRequest(pageIndex, pageSize);
		return this.videoAttributeService.getAttrs(pageable);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public void createAttr(@RequestBody String name) throws BusinessException {
		this.videoAttributeService.createAttr(name);
	}
}
