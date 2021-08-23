package com.springboot.lombok.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.Builder;

@Entity
@Table(name = "members")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class MemberEntity extends BaseEntity {


    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String authId;
    private String authPw;

    @Enumerated(EnumType.STRING)
    private Role role;

    
    private String name;
    private String phone;

    @Embedded
    private DeliveryAddress address;

    @Builder
    private MemberEntity(String authId, String authPw, String name, String phone,DeliveryAddress address) {
        this.role = Role.USER;
        this.authId = authId;
        this.authPw = authPw;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    
}
