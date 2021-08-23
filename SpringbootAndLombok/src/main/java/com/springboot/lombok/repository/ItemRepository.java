package com.springboot.lombok.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.lombok.model.ItemEntity;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

}
