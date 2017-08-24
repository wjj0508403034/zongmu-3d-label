package com.zongmu.label.dbox.user.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.zongmu.label.dbox.user.User;

@Repository
public interface UserRepo extends PagingAndSortingRepository<User, Long>,
		JpaSpecificationExecutor<User> {

	@Query("select t from User t where t.email = ?1")
	User findByEmail(String email);
}
