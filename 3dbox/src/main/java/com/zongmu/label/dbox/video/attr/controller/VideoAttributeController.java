package com.zongmu.label.dbox.video.attr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public VideoAttribute getAttr(@PathVariable(value = "id") Long id) throws BusinessException {
		return this.videoAttributeService.getAttr(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public void updateAttr(@PathVariable(value = "id") Long id, @RequestBody String name) throws BusinessException {
		this.videoAttributeService.updateAttr(id, name);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteAttr(@PathVariable(value = "id") Long id) throws BusinessException {
		this.videoAttributeService.deleteAttr(id);
	}

	@RequestMapping(value = "/{attrId}", method = RequestMethod.POST)
	@ResponseBody
	public void createAttrValue(@PathVariable(value = "attrId") Long attrId, @RequestBody String value)
			throws BusinessException {
		this.videoAttributeService.createAttrValue(attrId, value);
	}

	@RequestMapping(value = "/{attrId}/{valueId}", method = RequestMethod.PUT)
	@ResponseBody
	public void updateAttrValue(@PathVariable(value = "attrId") Long attrId,
			@PathVariable(value = "valueId") Long valueId, @RequestBody String value) throws BusinessException {
		this.videoAttributeService.updateAttrValue(attrId, valueId, value);
	}

	@RequestMapping(value = "/{attrId}/{valueId}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteAttrValue(@PathVariable(value = "attrId") Long attrId,
			@PathVariable(value = "valueId") Long valueId) throws BusinessException {
		this.videoAttributeService.deleteAttrValue(attrId, valueId);
	}
}
