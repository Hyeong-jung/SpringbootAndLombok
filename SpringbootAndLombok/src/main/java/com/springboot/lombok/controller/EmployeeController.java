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


//Lombok이 로거 필드를 생성
@Slf4j
@Controller
public class EmployeeController {

	
    @Autowired
    private EmployeeService service;
 
    // URL - http://localhost:10092/
    @GetMapping(value = "/employee/sorted/")
    public String viewIndexPage() {
        log.info("Redirecting the index page to the controller method for fetching the employees in a "
                + "paginated fashion.");
        // During the index page we are using the sort-field as id and sort-dir as asc.
        return "redirect:/employee/page/1?sort-field=id&sort-dir=asc";
    }
 
    // URL - http://localhost:10092/page/1?sort-field=firstName&sort-dir=desc
    @GetMapping(value = "/employee/page/{page-number}")
    public String findPaginated(@PathVariable(name = "page-number") final int pageNo,
                                @RequestParam(name = "sort-field") final String sortField,
                                @RequestParam(name = "sort-dir") final String sortDir,
                                final Model model) {
        log.info("Getting the employees in a paginated way for page-number = {}, sort-field = {}, and "
                + "sort-direction = {}.", pageNo, sortField, sortDir);
        // Hardcoding the page-size to 15.
        final int pageSize = 15;
        final Page<Employee> page = service.findPaginated(pageNo, pageSize, sortField, sortDir);
        final List<Employee> listEmployees = page.getContent();
 
        // Creating the model response.
        // Note for simplicity purpose we are not making the use of ResponseDto here.
        // In ideal cases the response will be encapsulated in a class.
        // pagination parameters
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        // sorting parameters
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        // employees
        model.addAttribute("listEmployees", listEmployees);
        //return "index";
        return "thymeleaf/employeeindex";
    }	
	
}
