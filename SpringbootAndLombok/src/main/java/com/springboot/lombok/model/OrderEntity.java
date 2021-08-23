package com.springboot.lombok.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.Builder;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEntity extends BaseEntity {

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    private int totalAmount;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    private boolean removed;
    private LocalDateTime removedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity orderer;
    
    
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private DeliveryEntity deliveryInformation;
    
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderItemEntity> orderItemList = new ArrayList<>();

    
    @Builder
    public OrderEntity(MemberEntity orderer, DeliveryEntity deliveryInformation, List<OrderItemEntity> orderItemEntityList) {
        this.orderer = orderer;
        this.deliveryInformation = deliveryInformation;
        this.setOrderItemList(orderItemEntityList);
        this.status = OrderStatus.ORDERED_STATUS;
    }

    private void setOrderItemList(List<OrderItemEntity> orderItemEntityList) {

        orderItemEntityList.stream()
                .forEach(orderItemEntity -> this.orderItemList.add(orderItemEntity));
        this.calculateTotalAmount();

    }

    private void calculateTotalAmount() {
        this.totalAmount = this.orderItemList.stream()
                .mapToInt(orderItem -> orderItem.getOrderItemAmount())
                .sum();
    }




    // ==== 비즈니스 로직 ====
    public void cancel() {
        if(this.deliveryInformation.getStatus() == DeliveryStatus.COMPLETE_STATUS
                || this.deliveryInformation.getStatus() == DeliveryStatus.SHIPPING_STATUS)
            throw new IllegalStateException("이미 배송중이거나 배송이 완료된 주문은 취소가 불가능합니다.");

        this.orderItemList.stream()
                .forEach(orderItem -> orderItem.cancel());

        this.status = OrderStatus.CANCEL_STATUS;
    }

    public void deleteOrder(Long ordererId) {
        if (ordererId != this.orderer.getMemberId())
            throw new IllegalStateException("주문자와 삭제 요청자가 일치하지 않습니다.");

        this.removed = true;
        this.removedAt = LocalDateTime.now();
    }


}
