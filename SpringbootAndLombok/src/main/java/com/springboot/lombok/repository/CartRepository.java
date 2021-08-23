package com.springboot.lombok.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.lombok.model.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, Long> { 
	CartEntity findFirstByMemberId(Long memberId);
}
