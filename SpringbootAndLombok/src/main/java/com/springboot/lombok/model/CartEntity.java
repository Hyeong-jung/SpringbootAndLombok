package com.springboot.lombok.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import com.springboot.lombok.exception.NotEnoughStockQuantityException;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart")
@Getter
@NoArgsConstructor
public class CartEntity {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    // Member를 참조하는 외래키 역할
    private Long memberId;

    
    // Map을 이용하므로써 @ElementCollection의 단점을 보완
    // itemId를 키로 사용
    // 1. 수정시, 모든 로우를 삭제 후, 수정된 로우를 추가하는 문제
    // 2. 삭제시, 모든 로우를 삭제 후, 삭제 대상을 제외한 모든 로우를 다시 입력하는 문제
    @ElementCollection
    @CollectionTable(
            name = "cart_line"
    )
    @MapKeyColumn(name = "map_key")
    private Map<Long, CartLine> cart = new HashMap<>();

    public CartEntity(Long memberId) {
        this.memberId = memberId;
    }

    

    public void addItemToCart(int targetStockQuantity, CartLine cartLine) {
        verifyEnoughStockQuantity(targetStockQuantity, cartLine.getOrderCount());

        Long mapKey = cartLine.getItemId();

        // 기존 아이템이 존재한다면 수량을 더함
        if (cart.containsKey(mapKey)) {
            CartLine existCartLine = cart.get(cartLine.getItemId());
            int newOrderCount = existCartLine.getOrderCount() + cartLine.getOrderCount();
            cart.replace(mapKey, new CartLine(cartId, cartLine.getItemId(), newOrderCount));
        }
        else {
            cart.put(mapKey, cartLine);
        }
    }

    public void modifyOrderCount(int targetStockQuantity, CartLine newCartLine) {
        verifyEnoughStockQuantity(targetStockQuantity, newCartLine.getOrderCount());

        this.cart.replace(newCartLine.getItemId(), newCartLine);
    }


    public void removeCartLine(Long cartItemId) {
        this.cart.remove(cartItemId);
    }

    
    private void verifyEnoughStockQuantity(int targetStockQuantity, int orderCount) {
        if (orderCount > targetStockQuantity) {
            throw new NotEnoughStockQuantityException("재고량이 충분하지 않습니다.");
        }
    }

}
