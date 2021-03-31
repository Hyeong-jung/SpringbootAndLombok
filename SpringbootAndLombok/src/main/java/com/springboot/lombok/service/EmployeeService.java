package com.springboot.lombok.service;


import com.springboot.lombok.model.Employee;
import com.springboot.lombok.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;
 
    public void save(final Employee employee) {
        repository.save(employee);
    }
 
    public long getTotalEmployees() {
        log.info("DB Employees 데이터 카운트");
        return repository.count();
    }
 
    public Page<Employee> findPaginated(final int pageNumber, final int pageSize, final String sortField, final String sortDirection) {
    	
        log.info("DB 에서 Sort 과 Pageable 처리된  Employees 데이터 출력");
        
        final Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();
                
        final Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        
        return repository.findAll(pageable);
    }	
	
}
