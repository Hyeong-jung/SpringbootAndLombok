package com.springboot.lombok.dto;

import com.springboot.lombok.model.Company;

public interface CompanyDto {

	Company findByName(String companyName);
	
}
