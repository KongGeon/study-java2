package com.knock.shop.comment;

import com.knock.shop.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //  여기에 함수 만들어 놓으면 서비스에서 가져다가 쓸 수 있음
    List<Comment> findAllByParentId(Long parentId); // 게시글 id 가져오는 함수 만들기
}
