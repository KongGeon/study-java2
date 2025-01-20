package com.knock.shop.item;


import org.springframework.data.domain.Page; // 이걸로 가져오기
import org.springframework.data.domain.Pageable;// 이걸로 가져오기
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
//    jpa함수 만드는 곳
Page<Item> findPageBy(Pageable page); //findPageBy 테이블에서 X개만 가져오는 함수
}

