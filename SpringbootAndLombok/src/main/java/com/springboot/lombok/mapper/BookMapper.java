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
	List<Book> selectBook();
	
	void insertBookByMapper(Map<String, String> map);
	
	
	//List<Map<String, String>> selectBookResultMap();
	List<Map<String, Object>> selectBookResultMap();
}


