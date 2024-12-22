package com.knock.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor //Lombok 문법, db입출력할때 사용
public class ItemController {
    private final ItemRepository itemRepository;// 인터페이시를 타입으로 하는 변수 , db입출력 함수들이 들어있음
    @GetMapping("/list")
    String list(Model model){//페이지에 접속하면 실행
        List<Item> result = itemRepository.findAll();
        model.addAttribute("items", result);
        return "list.html";
    }

    @GetMapping("/write")
    String write(){
        return "write.html";
    }
    @PostMapping("/add")
    String addPost(@RequestParam Map<String, String> formData) {
        String title = formData.get("title");
        Integer price = Integer.parseInt(formData.get("price"));
        Item item = new Item(title, price);

        // 데이터베이스에 저장
        itemRepository.save(item);
        // 로그 출력
        System.out.println("Saved Item: " + item);

        // 리다이렉트 확인
        return "redirect:/list"; // list 페이지로 이동
    }

    @GetMapping("/detail/{id}")
    String detail(@PathVariable Long id, Model model){ //@PathVariable Integer id => 주소에 적혀있는 id 불러오기
        Optional<Item> result = itemRepository.findById(id);
    if( result.isPresent()){ //  result가 비어 있으면 서버가 꺼질수 있어서 이렇게 비어있지 않은지 확인해야함
        System.out.println(result.get());
        model.addAttribute("items", result.get());
        return "detail.html";
    } else {
        return "redirect:/list";
    }


    }
}
