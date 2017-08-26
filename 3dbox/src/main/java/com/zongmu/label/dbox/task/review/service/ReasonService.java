package com.zongmu.label.dbox.task.review.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zongmu.label.dbox.error.BusinessException;
import com.zongmu.label.dbox.task.review.Reason;

public interface ReasonService {

	Page<Reason> getReasons(Pageable pageable) throws BusinessException;

	void createReason(String name) throws BusinessException;

	void updateReason(Long id, String name) throws BusinessException;

	void deleteReason(Long id) throws BusinessException;

	Reason getReason(Long id) throws BusinessException;

}
