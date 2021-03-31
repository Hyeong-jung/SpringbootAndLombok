package com.springboot.lombok.mapper;


import java.util.List;
import java.util.Map;

import com.springboot.lombok.model.Book;
import com.springboot.lombok.model.TestVo;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface BookMapper {
	
	// Mybatis List Mapper
	List<Book> selectBook();
	
	// Mybatis Insert Mapper
	void insertBookByMapper(Map<String, String> map);
	
	// Mybatis List Mapper By List<Map<String, Object>> 
	//List<Map<String, String>> selectBookResultMap();
	List<Map<String, Object>> selectBookResultMap();
}


