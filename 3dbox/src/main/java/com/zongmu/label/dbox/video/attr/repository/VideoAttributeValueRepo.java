package com.zongmu.label.dbox.video.attr.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.zongmu.label.dbox.video.attr.VideoAttributeValue;

@Repository
public interface VideoAttributeValueRepo extends PagingAndSortingRepository<VideoAttributeValue, Long>,
JpaSpecificationExecutor<VideoAttributeValue>{

}
