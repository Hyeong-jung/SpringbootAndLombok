package com.springboot.lombok.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.lombok.model.AddToCartRequestForm;
import com.springboot.lombok.model.CartEntity;
import com.springboot.lombok.model.CartLine;
import com.springboot.lombok.model.ModifyOrderCountRequestForm;
import com.springboot.lombok.repository.CartRepository;
import com.springboot.lombok.repository.ItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    //private final CartDao cartDao;

    public Long createCart(Long memberId) {
        return cartRepository.save(new CartEntity(memberId))
                .getCartId();
    }

    public void addItemToCart(Long memberId, AddToCartRequestForm addToCartRequestForm) {
        CartEntity cartEntity = cartRepository.findFirstByMemberId(memberId);

        CartLine newCartLine = new CartLine(cartEntity.getCartId(),
                addToCartRequestForm.getItemId(),
                addToCartRequestForm.getOrderCount());

        int targetStockQuantity = itemRepository.findById(addToCartRequestForm.getItemId())
                .get()
                .getStockQuantity();

        cartEntity.addItemToCart(targetStockQuantity, newCartLine);
    }
    
    //public List<CartLineDto> getCartInCartPage(Long memberId) {
    //return cartDao.getCartLineListInCartPage(memberId);
    //}
    

    public void modifyOrderCount(Long memberId, ModifyOrderCountRequestForm modifyOrderCountRequestForm) {
        // 엔티티 조회
        CartEntity cartEntity = cartRepository.findFirstByMemberId(memberId);
        int targetStockQuantity = itemRepository.findById(modifyOrderCountRequestForm.getItemId())
                .get()
                .getStockQuantity();

        CartLine newCartLine = new CartLine(cartEntity.getCartId(), modifyOrderCountRequestForm.getItemId(), modifyOrderCountRequestForm.getOrderCount());
        cartEntity.modifyOrderCount(targetStockQuantity, newCartLine);
    }

    //  특정 상품들만 주문하는 경우가 존재하므로, 장바구니를 그냥 비우는게 아닌, id를 기준으로 비워야함
    public void removeCartLines(Long memberId, List<Long> itemIds) {
        CartEntity cartEntity = cartRepository.findFirstByMemberId(memberId);

        itemIds.stream()
                .forEach(itemId -> cartEntity.removeCartLine(itemId));
    }

    public void removeCartLine(Long memberId, Long itemId) {
        CartEntity cartEntity = cartRepository.findFirstByMemberId(memberId);
        cartEntity.removeCartLine(itemId);
    }

    

}
