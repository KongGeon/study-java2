package com.knock.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
//보통 데이터를 보내주기 전에 비지니스로직(검사하거나 DB입출력하거나 하는 것)을 담는 클래스를 Service라고 부름
@RequiredArgsConstructor
public class MemberService {
    private  final MemberRepository memberRepository;

    public void saveMember(String password, String display_name, String username){
        Member member = new Member(password, display_name, username);
        // 데이터베이스에 저장
        memberRepository.save(member);
    }

}
