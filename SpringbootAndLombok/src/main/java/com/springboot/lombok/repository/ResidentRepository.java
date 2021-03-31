package com.springboot.lombok.repository;

import com.springboot.lombok.model.Resident;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ResidentRepository extends PagingAndSortingRepository<Resident, Integer> {

}
