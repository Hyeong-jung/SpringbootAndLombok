package com.springboot.lombok.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.lombok.exception.BookNotFoundException;
import com.springboot.lombok.model.Book;
import com.springboot.lombok.model.TestVo;
import com.springboot.lombok.service.BookService;
import com.springboot.lombok.service.TestService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Controller
public class TestController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());


	@RequestMapping(value = "/home")
	public String home(){ 
		return "index.html"; 
	}
	
	
	@ResponseBody 
	@RequestMapping("/valueTest") 
	public String valueTest(){ 
		String value = "테스트 String"; 
		return value; 
	}
	
	
	@RequestMapping("/testjsp") 
	public ModelAndView testjsp() throws Exception{ 
		ModelAndView mav = new ModelAndView("testjsp"); 
		mav.addObject("name", "JSP Test User Name"); 
		List<String> testList = new ArrayList<String>(); 
		testList.add("ArrayList1"); 
		testList.add("ArrayList2"); 
		testList.add("ArrayList3"); 
		mav.addObject("list", testList); 
		return mav; 
	}
	
	
	@RequestMapping("/thymeleafTest") 
	public String thymeleafTest(Model model) throws Exception{ 
			//TestVo testModel = new TestVo("Test Id", "Test Name");
			TestVo testModel = new TestVo();
			
			testModel.setId("Test Id");
			testModel.setName("Test Name");
			
			model.addAttribute("testModel", testModel);
			
			return "thymeleaf/thymeleafTest";
	}
	
		
	
	@Autowired 
	TestService testService;
	@RequestMapping("/testservice") 
	public ModelAndView testservice() throws Exception{ 
		ModelAndView mav = new ModelAndView("testservice");
		
		//logger.trace("Trace Level 테스트");
		//logger.debug("DEBUG Level 테스트");
		//logger.info("INFO Level 테스트");
		//logger.warn("Warn Level 테스트");
		//logger.error("ERROR Level 테스트");
	
		List<TestVo> testList = testService.selectTest(); 
		mav.addObject("list", testList);
			
		
		return mav; 
	}	

	
}
