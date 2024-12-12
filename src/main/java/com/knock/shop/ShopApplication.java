package com.knock.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopApplication {
//	여기에 함수 짜면 됨
//타입 함수명(){}

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
//		여기에 코드 짜면 됨

    }

}

class Friend{
    String name = "kim";
    int age= 20;
    Friend(){
//        new Friend 할때 자동 실행됨
        
    }
}