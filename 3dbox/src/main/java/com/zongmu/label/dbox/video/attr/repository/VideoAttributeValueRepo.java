package com.zongmu.label.dbox.video.attr.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.zongmu.label.dbox.video.attr.VideoAttribute;
import com.zongmu.label.dbox.video.attr.VideoAttributeValue;

@Repository
public interface VideoAttributeValueRepo
		extends PagingAndSortingRepository<VideoAttributeValue, Long>, JpaSpecificationExecutor<VideoAttributeValue> {

	@Query("select count(t) > 0 from VideoAttributeValue t where t.videoAttribute = ?1 and t.value = ?2")
	boolean exists(VideoAttribute attr, String value);

	@Query("select count(t) > 0 from VideoAttributeValue t where t.videoAttribute = ?1 and t.id <> ?2 and t.value = ?3")
	boolean exists(VideoAttribute videoAttribute, Long id, String value);
}
