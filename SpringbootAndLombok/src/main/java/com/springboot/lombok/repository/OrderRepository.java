package com.springboot.lombok.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.lombok.model.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
