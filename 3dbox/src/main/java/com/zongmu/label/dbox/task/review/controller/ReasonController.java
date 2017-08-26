package com.zongmu.label.dbox.task.review.controller;

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
import com.zongmu.label.dbox.task.review.Reason;
import com.zongmu.label.dbox.task.review.service.ReasonService;

@Controller
@RequestMapping(value = "/reasons")
public class ReasonController {

	@Autowired
	private ReasonService reasonService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Page<Reason> getReasons(
			@RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize)
			throws BusinessException {
		Pageable pageable = new PageRequest(pageIndex, pageSize);
		return this.reasonService.getReasons(pageable);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public void createReason(@RequestBody String name) throws BusinessException {
		this.reasonService.createReason(name);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Reason getReason(@PathVariable(value = "id") Long id) throws BusinessException {
		return this.reasonService.getReason(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public void updateReason(@PathVariable(value = "id") Long id, @RequestBody String name) throws BusinessException {
		this.reasonService.updateReason(id, name);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteReason(@PathVariable(value = "id") Long id) throws BusinessException {
		this.reasonService.deleteReason(id);
	}
}
