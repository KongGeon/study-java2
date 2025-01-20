package com.knock.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor //MemberRepository 이거 불러올떄 필요함
@Service
public class MyUserDetailsService implements UserDetailsService {
    //implements : MyUserDetailsService 클래스가 UserDetailsService(인터페이스) 따라하나 검사해주세요~
    //인터페이스 : 클래스가 어떻게 생겼는지 가이드라인 만드는 용(함수 정의만 넣는 클래스), 클래스에 특정 함수를 꼭 가지게 강요하고 싶을때 사용

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        DB에서 username을 가진 유저를 찾아와서
//        return new User(유저아이디, 비번, 권한) 해주세요

        var result = memberRepository.findByUsername(username); //유저가 제출한 아이디와 일치하는 컬럼 찾기
//        if(result.isPresent()){ // 옵셔널 타입은 if 문 써서 가져오는 것이 안전함
//            var user = result.get(); // 옵셔널로 데이터를 가져오기 때문에 .get() 이용해서 데이터 전달
//        }
        //위 처럼 해도되는데 아래가 더 편할거임
        if(result.isEmpty()){
            throw new UsernameNotFoundException("그런 아이디 없음");
        }
        var user = result.get(); // 옵셔널로 데이터를 가져오기 때문에 .get() 이용해서 데이터 전달
        List<GrantedAuthority>  authorities = new ArrayList<>(); //권한 리스트는 꼭 GrantedAuthority로 정의해야함
        authorities.add(new SimpleGrantedAuthority("일반유저")); //메모 느낌으로 적으면 됨, new SimpleGrantedAuthority 이런식으로 해야 나중에 API에ㅐ서 현재의 유저의 권한 출력 가능
       //return new User(user.getUsername(), user.getPassword(), authorities); // User에는 3개의 파라미터 밖에 넣을 수 없음 그래서 커스텀유저 하나 만들어서 추가하겠음
        var a = new CustomUser(user.getUsername(), user.getPassword(), authorities);
        a.displayName = user.getDisplayName();
        return a;
    }

}

