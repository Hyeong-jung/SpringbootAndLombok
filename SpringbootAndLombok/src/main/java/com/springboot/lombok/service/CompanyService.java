package com.springboot.lombok.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.springboot.lombok.repository.CompanyJpaRepository;
import com.springboot.lombok.model.Company;
import com.springboot.lombok.model.CompanyType;

@Service
public class CompanyService {

    @Autowired
    private CompanyJpaRepository companyJpaRepo;
 
    public List<Company> searchByNameAndType(String companyName, CompanyType type) {
        List<Company> companies = companyJpaRepo.findAll(new Specification<Company>() {
            @Override
            public Predicate toPredicate(Root<Company> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate matchType = cb.equal(root.<CompanyType> get("type"), type);
                Predicate returnPredicate = cb.and(matchType);
                if (!StringUtils.isEmpty(companyName)) {
                    Predicate matchName = cb.equal(root.<String> get("name"), companyName);
                    returnPredicate = cb.and(matchType, matchName);
                }
                return returnPredicate;
            }
        });
        return companies;
    }	
	
	
    public Company save(Company company) {
        return companyJpaRepo.save(company);
    }
     
    public void delete(Company company){
        companyJpaRepo.delete(company);
    }	
	
}
