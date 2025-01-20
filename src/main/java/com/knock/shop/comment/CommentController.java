package com.knock.shop.comment;

import com.knock.shop.member.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CommentController {


    private final CommentRepository commentRepository;
//    @PreAuthorize("") // 로그인 검사. 이렇게 하는거 맞나?
    @PostMapping("/comment") //서비스 파일쪽에 더 어울릴듯, 옮겨야 함
    String postComment(@RequestParam String content, @RequestParam Long parent, Authentication auth) { //content : 폼으로 보낸 값, auth:로그인된 유저 정보, parent: 게시글 정보
        CustomUser user = (CustomUser) auth.getPrincipal();
        System.out.println(user.getUsername());
        var data = new Comment();
        data.setContent(content);
        data.setParentId(parent);//게시글 아이디 저장
        data.setUsername(user.getUsername());// 작성자 정보 저장
        commentRepository.save(data); // 데이터 테이블에 저장
        return "redirect:/list/page/1";
    }
}
