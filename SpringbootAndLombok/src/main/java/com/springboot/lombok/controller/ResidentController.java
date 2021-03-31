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


//Causes Lombok to generate a logger field.
@Slf4j
@Controller
public class ResidentController {

    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;
    
    @Autowired
    private ResidentService service;    
	
    @GetMapping(value = "/residents/paginated/")
    public String viewIndexPage() {
        log.info("Redirecting the index page to the controller method for fetching the residents in a paginated fashion.");
        return "redirect:/residents/paginated/" + DEFAULT_PAGE_NUMBER + "/" + DEFAULT_PAGE_SIZE;
    }
    
    @GetMapping(value = "/residents/paginated/{page}/{page-size}")
    public String getPaginatedResidents(@PathVariable(name = "page") final int pageNumber,
                                        @PathVariable(name = "page-size") final int pageSize, final Model model) {
        log.info("Getting the residents in a paginated way for page-number = {} and page-size = {}.", pageNumber, pageSize);
        
        final Page<Resident> paginatedResidents = service.getPaginatedResidents(pageNumber, pageSize);
        model.addAttribute("responseEntity", createResponseDto(paginatedResidents, pageNumber));
        
        //return "index";
        return "thymeleaf/residentindex";
    }
    
    private ResponseDto createResponseDto(final Page<Resident> residentPage, final int pageNumber) {
        final Map<String, Integer> page = new HashMap<>();
        page.put("currentPage", pageNumber);
        /*
         Here we are fetching the total number of records from the Page interface of the Spring itself.
         We can also customize this logic based on the total number of elements retrieved from the query.
        */
        page.put("totalPages", residentPage.getTotalPages());
        page.put("totalElements", (int) residentPage.getTotalElements());
        return ResponseDto.create(residentPage.getContent(), page);
    }    
     
}
