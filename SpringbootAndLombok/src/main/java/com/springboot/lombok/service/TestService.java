package com.springboot.lombok.service;

import com.springboot.lombok.mapper.TestMapper;
import com.springboot.lombok.model.TestVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class TestService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public TestMapper mapper;
	
	public List<TestVo> selectTest() {
		
		//logger.trace("Trace Level 테스트");
		//logger.debug("DEBUG Level 테스트");
		//logger.info("INFO Level 테스트");
		//logger.warn("Warn Level 테스트");
		//logger.error("ERROR Level 테스트");		
		
		
		return mapper.selectTest(); 
	}	
	
}
