package com.akash.truyum.mainapp.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.akash.truyum.mainapp.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

	@Query("From Cart where userId=:user")
	List<Cart> findAllByUserId(Integer user);

	@Transactional
	@Modifying
	@Query("Delete From Cart c where c.menuId=:id")
	void deleteByMenuItemId(Integer id);

}
