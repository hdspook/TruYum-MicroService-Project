package com.akash.userDetails.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.akash.userDetails.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	@Query("FROM User where user_id = :userId")
	User findAdminByUsername(String userId);

	@Query(value = "select role from user where user_id=:username", nativeQuery = true)
	String getAuthority(String username);

	@Query(value = "SELECT username from user where user_id =:id ", nativeQuery = true)
	String findUsernameById(Integer id);

}
