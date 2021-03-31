package com.springboot.lombok.mapper;

import java.util.List;

import com.springboot.lombok.model.TestVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;



@Repository
@Mapper
public interface TestMapper {
	List<TestVo> selectTest();
}
