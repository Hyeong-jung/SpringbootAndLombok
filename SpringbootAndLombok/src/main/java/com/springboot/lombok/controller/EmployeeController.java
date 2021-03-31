package com.springboot.lombok.controller;

import com.springboot.lombok.model.Employee;
import com.springboot.lombok.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
 
import java.util.List;


//Lombok 로거 필드 생성
@Slf4j
@Controller
public class EmployeeController {

	
    @Autowired
    private EmployeeService service;
 
    // URL - http://localhost:10092/
    @GetMapping(value = "/employee/sorted/")
    public String viewIndexPage() {
        log.info(" Index 페이지 sort-field(정렬필드) sort-dir(정렬방향)");
        // Index 페이지 sort-field(정렬필드) sort-dir(정렬방향)
        return "redirect:/employee/page/1?sort-field=id&sort-dir=asc";
    }
 
    // URL - http://localhost:8080/page/1?sort-field=firstName&sort-dir=desc
    @GetMapping(value = "/employee/page/{page-number}")
    public String findPaginated(@PathVariable(name = "page-number") final int pageNo,
                                @RequestParam(name = "sort-field") final String sortField,
                                @RequestParam(name = "sort-dir") final String sortDir,
                                final Model model) {
        log.info("페이지 번호 = {}, 정렬 필드 = {} 및 정렬 방향 = {}.", pageNo, sortField, sortDir);

        final int pageSize = 15;
        final Page<Employee> page = service.findPaginated(pageNo, pageSize, sortField, sortDir);
        final List<Employee> listEmployees = page.getContent();
 
        // model response 생성

        model.addAttribute("currentPage", pageNo); // 현재 페이지
        model.addAttribute("totalPages", page.getTotalPages()); // 전체 페이지
        model.addAttribute("totalItems", page.getTotalElements()); // 전체 데이터 카운트
        model.addAttribute("sortField", sortField); // 정렬필드
        model.addAttribute("sortDir", sortDir); // 정렬방향
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc"); // 정렬값
        model.addAttribute("listEmployees", listEmployees); // 리스트 데이터 모델
        //return "index";
        return "thymeleaf/employeeindex";
    }	
	
}
