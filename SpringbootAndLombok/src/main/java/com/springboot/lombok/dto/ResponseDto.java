package com.springboot.lombok.dto;

import com.springboot.lombok.model.Resident;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ResponseDto {
	
    private final List<Resident> residents;
    private final Map<String, Integer> page;
    

    public ResponseDto(final List<Resident> residents, final Map<String, Integer> page) {
        this.residents = new ArrayList<>(residents);
        this.page = new HashMap<>(page);
    }    
    
    
    public static ResponseDto create(final List<Resident> residents, final Map<String, Integer> page) {
        return new ResponseDto(residents, page);
    }

    public List<Resident> getResidents() {
        return residents;
    }

    public Map<String, Integer> getPage() {
        return page;
    }    

}
