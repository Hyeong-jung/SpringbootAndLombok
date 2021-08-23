package com.springboot.lombok.model;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
public class ModifyOrderCountRequestForm {
    private Long itemId;
    @Min(1)
    private int orderCount;

}
