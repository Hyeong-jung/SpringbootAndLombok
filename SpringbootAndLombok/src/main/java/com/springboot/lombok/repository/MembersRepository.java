package com.springboot.lombok.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.lombok.model.MemberEntity;

import java.util.Optional;


public interface MembersRepository extends JpaRepository<MemberEntity, Long> {
	Optional<MemberEntity> findFirstByAuthId(String authId);
}
