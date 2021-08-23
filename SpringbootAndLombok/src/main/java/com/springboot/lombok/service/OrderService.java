package com.springboot.lombok.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.lombok.model.DeliveryEntity;
import com.springboot.lombok.model.ItemEntity;
import com.springboot.lombok.model.MemberEntity;
import com.springboot.lombok.model.OrderEntity;
import com.springboot.lombok.model.OrderItemEntity;
import com.springboot.lombok.model.OrderRequest;
import com.springboot.lombok.repository.ItemRepository;
import com.springboot.lombok.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor

public class OrderService {

	
    private final OrderRepository orderRepository;
    private final MembersService memberService;
    private final CartService cartService;
    private final ItemRepository itemRepository;

    public Long order(Long ordererId, OrderRequest orderRequest) {
        // 엔티티 조회
        MemberEntity orderer = memberService.findMember(ordererId);
        
        
        // 배송지 생성
        DeliveryEntity deliveryEntity = DeliveryEntity.builder()
                .address(orderer.getAddress())
                .build();
        
        // 주문상품 생성
        List<OrderItemEntity> orderItemEntityList = orderRequest.getOrderLineList()
                .stream()
                .map(ol -> {
                    ItemEntity itemEntity = itemRepository.findById(ol.getItemId())
                            .get();
                    return new OrderItemEntity(itemEntity, ol.getOrderCount());
                })
                .collect(Collectors.toList());

        // 주문 상품 재고 줄이기
        orderItemEntityList.stream()
                .forEach(oi -> oi.removeStockQuantity());

        

        // 주문 생성
        OrderEntity orderEntity = OrderEntity.builder()
                .orderer(orderer)
                .deliveryInformation(deliveryEntity)
                .orderItemEntityList(orderItemEntityList)
                .build();



        // 장바구니 비우기 (특정 상품들만 주문하는 경우가 존재하므로, 장바구니를 그냥 비우는게 아닌, id를 기준으로 비워야함)
        List<Long> itemIdList = orderRequest.getOrderLineList().stream()
                .map(ol -> ol.getItemId())
                .collect(Collectors.toList());
        cartService.removeCartLines(orderer.getMemberId(), itemIdList);

        // 주문 저장
        return orderRepository.save(orderEntity).getOrderId();

    }
    
    public void cancel(Long orderId) {
        OrderEntity order = orderRepository.findById(orderId).get();
        order.cancel();
    }

    
}
