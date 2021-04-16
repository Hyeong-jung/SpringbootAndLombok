package com.springboot.lombok.repository;


import java.util.Optional;
import com.springboot.lombok.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserInfo, Long> {

	Optional<UserInfo> findByEmail(String email);
	
}
