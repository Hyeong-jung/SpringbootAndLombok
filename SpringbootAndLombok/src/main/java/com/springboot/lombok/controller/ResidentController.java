package com.springboot.lombok.controller;


import com.springboot.lombok.dto.ResponseDto;
import com.springboot.lombok.model.Resident;
import com.springboot.lombok.service.ResidentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;


// Lombok 로거 필드 생성
@Slf4j
@Controller
public class ResidentController {

    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;
    
    @Autowired
    private ResidentService service;    
	
    @GetMapping(value = "/residents/paginated/")
    public String viewIndexPage() {
        log.info("Index 페이지 이동");
        return "redirect:/residents/paginated/" + DEFAULT_PAGE_NUMBER + "/" + DEFAULT_PAGE_SIZE;
    }
    
    @GetMapping(value = "/residents/paginated/{page}/{page-size}")
    public String getPaginatedResidents(@PathVariable(name = "page") final int pageNumber,
                                        @PathVariable(name = "page-size") final int pageSize, final Model model) {
        log.info("페이지 번호 = {} 및 페이지 크기 = {}.", pageNumber, pageSize);
        
        final Page<Resident> paginatedResidents = service.getPaginatedResidents(pageNumber, pageSize);
        model.addAttribute("responseEntity", createResponseDto(paginatedResidents, pageNumber));
        
        //return "index";
        return "thymeleaf/residentindex";
    }
    
    private ResponseDto createResponseDto(final Page<Resident> residentPage, final int pageNumber) {

    	final Map<String, Integer> page = new HashMap<>();
        page.put("currentPage", pageNumber); // 현재 페이지
        page.put("totalPages", residentPage.getTotalPages()); // 전체 페이지 수
        page.put("totalElements", (int) residentPage.getTotalElements()); // Spring 자체 Page 인터페이스에서 가져온 총 레코드 수
        
        return ResponseDto.create(residentPage.getContent(), page);
    }    
     
}
