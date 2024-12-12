package com.knock.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor //Lombok 문법, db입출력할때 사용
public class ItemController {
    private final ItemRepository itemRepository;// 인터페이시를 타입으로 하는 변수 , db입출력 함수들이 들어있음
    @GetMapping("/list")
    String list(Model model){//페이지에 접속하면 실행
        model.addAttribute("name", "홍길동");
        List<Item> result = itemRepository.findAll();
        System.out.println(result.get(0).price);
        System.out.println(result.get(1).price);
        System.out.println(result.get(2).price);

        return "list.html";
    }
}
