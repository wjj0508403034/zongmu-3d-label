package com.zongmu.label.dbox.task.review.service.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.zongmu.label.dbox.core.entity.BusinessService;
import com.zongmu.label.dbox.error.BusinessException;
import com.zongmu.label.dbox.error.ErrorCodes;
import com.zongmu.label.dbox.task.review.Reason;
import com.zongmu.label.dbox.task.review.repository.ReasonRepo;
import com.zongmu.label.dbox.task.review.service.ReasonService;

public class ReasonServiceImpl extends BusinessService implements ReasonService {

	private ReasonRepo reasonRepo;

	public ReasonServiceImpl(ApplicationContext applicationContext) {
		super(applicationContext);
		this.reasonRepo = this.getBean(ReasonRepo.class);
	}

	@Override
	public Page<Reason> getReasons(Pageable pageable) throws BusinessException {
		return this.reasonRepo.findAll(pageable);
	}

	@Transactional
	@Override
	public void createReason(String name) throws BusinessException {
		if (this.reasonRepo.exists(name)) {
			throw new BusinessException(ErrorCodes.Reason_Exists);
		}

		Reason reason = new Reason();
		reason.setName(name);
		this.reasonRepo.save(reason);
	}

	@Transactional
	@Override
	public void updateReason(Long id, String name) throws BusinessException {

		if (this.reasonRepo.existsExceptSelf(name, id)) {
			throw new BusinessException(ErrorCodes.Reason_Exists);
		}

		Reason reason = this.getReason(id);
		reason.setName(name);
		this.reasonRepo.save(reason);
	}

	@Transactional
	@Override
	public void deleteReason(Long id) throws BusinessException {
		Reason reason = this.getReason(id);
		// TODO: 检查是否已经被使用

		this.reasonRepo.delete(reason);
	}

	@Override
	public Reason getReason(Long id) throws BusinessException {
		Reason reason = this.reasonRepo.findOne(id);
		if (reason == null) {
			throw new BusinessException(ErrorCodes.Reason_Not_Exists);
		}
		return reason;
	}

}
