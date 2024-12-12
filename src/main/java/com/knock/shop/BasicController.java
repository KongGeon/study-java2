package com.knock.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.ZonedDateTime;

@Controller //이거 클래스에 붙이면 아래에서 서버기능 제작 가능
public class BasicController {
    @GetMapping("/") //누가 메인페이지에 접속하면
//    @ResponseBody  // 이거 있으면 리턴값이 텍스트로 넘어감
    String hello(){
        return "index.html"; //이거 보내주세요
    }
    @GetMapping("/index") //누가 메인페이지에 접속하면
    @ResponseBody
    String hello2(){
        return "hello"; //이거 보내주세요
    }
    @GetMapping("/date") //누가 메인페이지에 접속하면
    @ResponseBody
    String date(){
        return ZonedDateTime.now().toString();
    }
}
