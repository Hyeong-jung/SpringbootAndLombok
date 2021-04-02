package com.springboot.lombok.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.lombok.model.Company;
import com.springboot.lombok.model.CompanyType;

@Repository
public interface CompanyJpaRepository extends JpaRepository<Company, Long> {
	
	 // 정적 쿼리 + 동적 쿼리 JpaRepository 데모 클래스
    @Query("SELECT req FROM Company req  WHERE req.type=(:type) AND req.name= (:name)")
    List<Company> findByTypeAndName(@Param("type") CompanyType type, @Param("name") String name);
 
    List<Company> findAll(Specification<Company> specification);	
	

}
