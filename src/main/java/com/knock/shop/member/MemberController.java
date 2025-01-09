package com.knock.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor //Lombok 문법, db입출력할때 사용
public class MemberController {
    private final MemberRepository memberRepository;// 인터페이시를 타입으로 하는 변수 , db입출력 함수들이 들어있음
    private  final MemberService memberService; //서비스 파일 불러오기, 이렇게 변수 하나에 불러와서 재사용하는것이 권장됨
    private  final PasswordEncoder passwordEncoder; //BCryptPasswordEncoder 불러오기용으로 만든 파일

    @GetMapping("/member")
    String member(){


        return "member.html";
    }
    @PostMapping("/addMember")
    String addMember(String password, String display_name, String username) {
        var encoder = passwordEncoder;
        var new_password = encoder.encode(password);
        memberService.saveMember(new_password, display_name, username);

        // 리다이렉트 확인
        return "redirect:/list"; // list 페이지로 이동
    }

    @GetMapping("/login")
    public String login(Authentication auth){
        if (!(auth == null)){ //로그인 상태에서 로그인페이지 오면 리스트페이지로 이동
            return "redirect:/list";
        }
        return "login.html";
    }

    @GetMapping("/my-page")
    public String myPage(Authentication auth) {
        //        MyUserDetailsService에서 일반 User()로 만들었으면 auth만 쓰면 되는데 커스텀유저로 새로 만들어서 auth.getPrincipal()로 써야함

        CustomUser result = (CustomUser) auth.getPrincipal();

        System.out.println(auth);
        System.out.println(result.displayName);
        System.out.println(auth.getName()); //아이디출력가능
        System.out.println(auth.isAuthenticated()); //로그인여부 검사가능
        System.out.println(auth.getAuthorities().contains(new SimpleGrantedAuthority("일반유저")));
        return "mypage.html";
    }
}
