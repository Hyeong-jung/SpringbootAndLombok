package com.springboot.lombok.dto.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.springboot.lombok.dto.CompanyDto;
import com.springboot.lombok.model.Company;
import com.springboot.lombok.model.CompanyType;


@Component
public class CompanyDaoImpl implements CompanyDto {

    private static final String SQL_SEARCH_COMPANY_BY_NAME = "Select id CompanyID, name CompanyName, type from Company where name = :companyName";
    
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
 
    @Override
    public Company findByName(String companyName) {
        Company ret = null;
        try {
            ret = jdbcTemplate.queryForObject(SQL_SEARCH_COMPANY_BY_NAME,
                    Collections.singletonMap("companyName", companyName), new CompanyRowMapper());
        } catch (IncorrectResultSizeDataAccessException e) {
            // 예외처리
        }
        return ret;
    }
 
    private static final class CompanyRowMapper implements RowMapper<Company> {
        public Company mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            final Company company = new Company();
            company.setId(resultSet.getLong("CompanyID"));
            company.setName(resultSet.getString("CompanyName"));
            company.setType(CompanyType.valueOf(resultSet.getString("type")));
 
            return company;
        }
    }	
	
}
