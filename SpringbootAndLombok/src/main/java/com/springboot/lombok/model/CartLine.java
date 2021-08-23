package com.springboot.lombok.model;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;


@Embeddable
@NoArgsConstructor
@Getter
public class CartLine {

    private Long cartId;
    private Long itemId;
    private Integer orderCount;

    public CartLine(Long cartId, Long itemId, Integer orderCount) {
        this.cartId = cartId;
        this.itemId = itemId;
        this.orderCount = orderCount;
    }

	
}
