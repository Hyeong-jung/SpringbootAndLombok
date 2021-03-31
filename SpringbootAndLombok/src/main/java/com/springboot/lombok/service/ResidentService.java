package com.springboot.lombok.service;

import com.springboot.lombok.model.Resident;
import com.springboot.lombok.repository.ResidentRepository;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class ResidentService {

    @Autowired
    private ResidentRepository repository;
 
    public void save(final Resident resident) {
        repository.save(resident);
    }
 
    public long getResidentsCount() {
        log.info("DB Residents 데이터 카운트");
        return repository.count();
    }
 
    public Page getPaginatedResidents(final int pageNumber, final int pageSize) {
    	
    	log.info("DB 에서 Pageable 처리된  Residents 데이터 출력");
        
        final Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        
        return repository.findAll(pageable);
    }
	
}
