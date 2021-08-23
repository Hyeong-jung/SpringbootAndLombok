package com.springboot.lombok.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "delivery")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryEntity extends BaseEntity {

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long deliveryId;
    private DeliveryAddress address;

    @Enumerated(value = EnumType.STRING)
    private DeliveryStatus status;


    @Builder
    public DeliveryEntity(DeliveryAddress address) {
        this.address = address;
        this.status = DeliveryStatus.READY_STATUS;
    }

}
