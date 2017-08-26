package com.zongmu.label.dbox.task.review.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.zongmu.label.dbox.task.review.Reason;

@Repository
public interface ReasonRepo extends PagingAndSortingRepository<Reason, Long>, JpaSpecificationExecutor<Reason> {

	@Query("select count(t) > 0 from Reason t where t.name = ?1")
	boolean exists(String name);

	@Query("select count(t) > 0 from Reason t where t.name = ?1 and t.id <> ?2")
	boolean existsExceptSelf(String name, Long id);

}
