package com.knock.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor //Lombok 문법, db입출력할때 사용
public class ItemController {
    private final ItemRepository itemRepository;// 인터페이시를 타입으로 하는 변수 , db입출력 함수들이 들어있음
    private  final ItemService itemService; //서비스 파일 불러오기, 이렇게 변수 하나에 불러와서 재사용하는것이 권장됨
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
    String addPost(String title, Integer price) {
        itemService.saveItem(title, price);

        // 리다이렉트 확인
        return "redirect:/list"; // list 페이지로 이동
    }

    @GetMapping("/update/{id}")
    String update(@PathVariable Long id,Model model){
        Optional<Item> result = itemRepository.findById(id);
        model.addAttribute("items", result.get());
        return "update.html";
    }
    @PostMapping("/update")
    String updatePost(Long id, String title, Integer price) {
        itemService.updateItem(id, title, price);

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

    @PostMapping("/test")
    String test(@RequestBody Map<String, Object> body) {
        System.out.println(body.get("title"));
        return "redirect:/list";
    }

    @DeleteMapping("/deleteItem")
    ResponseEntity<String> deleteItem(@RequestParam Long id) {
        System.out.println(id);
        itemRepository.deleteById(id);

        // 리다이렉트 확인
        return ResponseEntity.status(200).body("삭제완료"); // list 페이지로 이동
    }
}
