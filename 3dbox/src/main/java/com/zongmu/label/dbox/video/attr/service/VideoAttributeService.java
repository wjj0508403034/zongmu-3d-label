package com.zongmu.label.dbox.video.attr.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zongmu.label.dbox.error.BusinessException;
import com.zongmu.label.dbox.video.attr.VideoAttribute;

public interface VideoAttributeService {

	Page<VideoAttribute> getAttrs(Pageable pageable);

	void createAttr(String name) throws BusinessException;

	VideoAttribute getAttr(Long id) throws BusinessException;

	void updateAttr(Long id, String name) throws BusinessException;

	void deleteAttr(Long id) throws BusinessException;

	void createAttrValue(Long attrId, String value) throws BusinessException;

	void updateAttrValue(Long attrId, Long valueId, String value) throws BusinessException;

	void deleteAttrValue(Long attrId, Long valueId) throws BusinessException;

}
