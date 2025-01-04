package com.knock.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Bean 쓸때 필요
@EnableWebSecurity
public class SecurityConfig {
    @Bean // Bean : 스프링이 뽑은 오브젝트
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable());//csrf 방지를 잠시 끔, 개발때에만 사용
        //filterChain : 모든 유저의 요청과 서버의 응답사이에 자동으로 실행해주고 싶은 코드를 담는 곳
        http.authorizeHttpRequests((authorize) ->
//                authorize.requestMatchers("/**").permitAll() //특정 페이지 로그인 검사 할지 결정 가능, permitAll():아무나 접속허용 , "/**" : 모든 주소
                authorize.requestMatchers("/**").permitAll()
        );
        http.formLogin((formLogin) -> formLogin.loginPage("/login") //폼으로 로그인
                .defaultSuccessUrl("/")//로그인 성공시 이동시킬 url
//                .failureUrl("/fail")//실패시 이동될 URL, 이런거 안적어놓으면 /login?error 페이지로 이동
        );
        http.logout( logout -> logout.logoutUrl("/logout") ); //겟요청하면 로그아웃

        return http.build();
    }
}