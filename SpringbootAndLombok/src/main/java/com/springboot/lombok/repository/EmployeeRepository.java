package com.springboot.lombok.repository;


import com.springboot.lombok.model.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>  {
	
	

}
