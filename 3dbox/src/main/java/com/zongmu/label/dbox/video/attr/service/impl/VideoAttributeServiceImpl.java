package com.zongmu.label.dbox.video.attr.service.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.zongmu.label.dbox.core.entity.BusinessService;
import com.zongmu.label.dbox.error.BusinessException;
import com.zongmu.label.dbox.error.ErrorCodes;
import com.zongmu.label.dbox.video.attr.VideoAttribute;
import com.zongmu.label.dbox.video.attr.VideoAttributeValue;
import com.zongmu.label.dbox.video.attr.repository.VideoAttributeRepo;
import com.zongmu.label.dbox.video.attr.repository.VideoAttributeValueRepo;
import com.zongmu.label.dbox.video.attr.service.VideoAttributeService;

public class VideoAttributeServiceImpl extends BusinessService implements VideoAttributeService {

	private VideoAttributeRepo videoAttrRepo;
	private VideoAttributeValueRepo videoAttrValueRepo;

	public VideoAttributeServiceImpl(ApplicationContext applicationContext) {
		super(applicationContext);

		this.videoAttrRepo = this.getBean(VideoAttributeRepo.class);
		this.videoAttrValueRepo = this.getBean(VideoAttributeValueRepo.class);
	}

	@Override
	public Page<VideoAttribute> getAttrs(Pageable pageable) {
		return this.videoAttrRepo.findAll(pageable);
	}

	@Transactional
	@Override
	public void createAttr(String name) throws BusinessException {
		if (this.videoAttrRepo.exists(name)) {
			throw new BusinessException(ErrorCodes.Video_Attr_Exists);
		}
		VideoAttribute attr = new VideoAttribute();
		attr.setName(name);
		this.videoAttrRepo.save(attr);
	}

	@Transactional
	@Override
	public void updateAttr(Long id, String name) throws BusinessException {
		if (this.videoAttrRepo.existsExceptSelf(name, id)) {
			throw new BusinessException(ErrorCodes.Video_Attr_Exists);
		}

		VideoAttribute attr = this.videoAttrRepo.findOne(id);
		if (attr == null) {
			throw new BusinessException(ErrorCodes.Video_Attr_Not_Exists);
		}

		attr.setName(name);
		this.videoAttrRepo.save(attr);
	}

	@Transactional
	@Override
	public void deleteAttr(Long id) throws BusinessException {
		VideoAttribute attr = this.getAttr(id);
		// TODO: 要检查属性是否被用，如果被用了，就不能删除
		this.videoAttrRepo.delete(attr);
	}

	@Override
	public VideoAttribute getAttr(Long id) throws BusinessException {
		VideoAttribute attr = this.videoAttrRepo.findOne(id);
		if (attr == null) {
			throw new BusinessException(ErrorCodes.Video_Attr_Not_Exists);
		}

		return attr;
	}

	@Transactional
	@Override
	public void createAttrValue(Long attrId, String value) throws BusinessException {
		VideoAttribute attr = this.getAttr(attrId);
		if (this.videoAttrValueRepo.exists(attr, value)) {
			throw new BusinessException(ErrorCodes.Video_Attr_Value_Exists);
		}

		VideoAttributeValue attrValue = new VideoAttributeValue();
		attrValue.setValue(value);
		attrValue.setVideoAttribute(attr);
		this.videoAttrValueRepo.save(attrValue);
	}

	@Transactional
	@Override
	public void updateAttrValue(Long attrId, Long valueId, String value) throws BusinessException {
		VideoAttributeValue attrValue = this.getAttrValue(valueId);

		if (this.videoAttrValueRepo.exists(attrValue.getVideoAttribute(), attrValue.getId(), value)) {
			throw new BusinessException(ErrorCodes.Video_Attr_Value_Exists);
		}

		attrValue.setValue(value);
		this.videoAttrValueRepo.save(attrValue);
	}

	@Transactional
	@Override
	public void deleteAttrValue(Long attrId, Long valueId) throws BusinessException {
		VideoAttributeValue attrValue = this.getAttrValue(valueId);

		// TODO: 要检查属性值是否被用，如果被用了，就不能删除
		this.videoAttrValueRepo.delete(attrValue);
	}

	private VideoAttributeValue getAttrValue(Long valueId) throws BusinessException {
		VideoAttributeValue attrValue = this.videoAttrValueRepo.findOne(valueId);
		if (attrValue == null) {
			throw new BusinessException(ErrorCodes.Video_Attr_Value_Not_Exists);
		}

		return attrValue;
	}

}
