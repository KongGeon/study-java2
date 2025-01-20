package com.knock.shop.item;

import com.knock.shop.comment.Comment;
import com.knock.shop.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor //Lombok 문법, db입출력할때 사용
public class ItemController {
    private final CommentRepository commentRepository;
    private final ItemRepository itemRepository;// 인터페이시를 타입으로 하는 변수 , db입출력 함수들이 들어있음
    private  final ItemService itemService; //서비스 파일 불러오기, 이렇게 변수 하나에 불러와서 재사용하는것이 권장됨
    @GetMapping("/list")
    String list(Model model){//페이지에 접속하면 실행
        List<Item> result = itemRepository.findAll();
        model.addAttribute("items", result);
        return "list.html";
    }
    @GetMapping("/list/page/{page}") //페이지네이션
    String getListPage(Model model, @PathVariable Integer page) {
//        var result = "1번부터 5번글 가져와주세요~";
        Page<Item> result = itemRepository.findPageBy(PageRequest.of(page - 1, 3)); //PageRequest.of(0, 5) 0번째 페이지, 페이자당 5개
        int totalPages = result.getTotalPages();
        int currentPage = result.getNumber() + 1; // 현재 페이지 (1-based)
        int groupSize = 2;                       // 한 번에 보여줄 페이지 개수

        // (currentPage - 1) / groupSize → 0-based 그룹 인덱스
        int groupIndex = (currentPage - 1) / groupSize;

        // 그룹의 시작/끝 페이지 계산
        int startPage = groupIndex * groupSize + 1;
        int endPage = startPage + groupSize - 1;
        if (endPage > totalPages) {
            endPage = totalPages;
        }

        // 뷰에서 사용할 데이터 세팅
        model.addAttribute("items", result.getContent()); // 실제 아이템 목록
        model.addAttribute("currentPage", currentPage);    // 현재 페이지
        model.addAttribute("totalPages", totalPages);      // 전체 페이지 수
        model.addAttribute("startPage", startPage);        // 현재 보이는 페이지 시작
        model.addAttribute("endPage", endPage);            // 현재 보이는 페이지 끝

        return "list.html";
    }

    @GetMapping("/write")
    String write(){
        return "write.html";
    }
    @PostMapping("/add")
    String addPost(String title, Integer price, Authentication auth) {
        String username = auth.getName();
        itemService.saveItem(title, price, username);
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

    @GetMapping("/detail/{id}") // 상세페이지
    String detail(@PathVariable Long id, Model model){ //@PathVariable Integer id => 주소에 적혀있는 id 불러오기
        Optional<Item> result = itemRepository.findById(id);
        var res = commentRepository.findAllByParentId(id);
        if( result.isPresent()){ //  result가 비어 있으면 서버가 꺼질수 있어서 이렇게 비어있지 않은지 확인해야함
            System.out.println(result.get());
            model.addAttribute("items", result.get());
            model.addAttribute("comment", res);
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

    //    회원가입


}
