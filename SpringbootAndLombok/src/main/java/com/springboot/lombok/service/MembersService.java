package com.springboot.lombok.service;

import java.util.List;
import java.util.Optional;

//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.lombok.model.DeliveryAddress;
import com.springboot.lombok.model.MemberEntity;
import com.springboot.lombok.model.SignUpRequest;
import com.springboot.lombok.repository.MembersRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class MembersService {

	
    private final MembersRepository memberRepository;
    private final CartService cartService;
    //private final PasswordEncoder encoder;
	
    public Long signUp(SignUpRequest request) {
        validateDuplicateMember(request.getAuthId());

        // 회원 저장
        MemberEntity newMember = MemberEntity.builder()
                .address(new DeliveryAddress(request.getCity(), request.getStreet()))
                .authId(request.getAuthId())
                //.authPw(encoder.encode(request.getPassword()))
                .authPw(request.getPassword())
                .name(request.getName())
                .phone(request.getPhone())
                .build();
        Long memberId = memberRepository.save(newMember).getMemberId();

        // 회원 장바구니 생성
        cartService.createCart(memberId);

        return memberId;
    }


    private void validateDuplicateMember(String authId) {
        Optional<MemberEntity> findMember = memberRepository.findFirstByAuthId(authId);

        if(findMember.isPresent())
            throw new IllegalStateException("이미 존재하는 회원입니다.");
    }
    
    private MemberEntity validateExistMember(Optional<MemberEntity> memberEntity) {
        if(!memberEntity.isPresent())
            throw new IllegalStateException("존재하지 않는 유저입니다.");
        return memberEntity.get();
    }

    public MemberEntity findMember(Long memberId) {
        MemberEntity memberEntity = validateExistMember(memberRepository.findById(memberId));

        return memberEntity;
    }


    public List<MemberEntity> findAllMember() {
        return memberRepository.findAll();
    }

}
