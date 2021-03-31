package com.springboot.lombok.mapper;

import java.util.List;

import com.springboot.lombok.model.TestVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;



@Repository
@Mapper
public interface TestMapper {
	// Mybatis 테스트용 mapper 생성
	List<TestVo> selectTest();
}
