package com.zongmu.label.dbox.video.attr.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.zongmu.label.dbox.video.attr.VideoAttribute;

@Repository
public interface VideoAttributeRepo extends PagingAndSortingRepository<VideoAttribute, Long>,
JpaSpecificationExecutor<VideoAttribute>{

}
