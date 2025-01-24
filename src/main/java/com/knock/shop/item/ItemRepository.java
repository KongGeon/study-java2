package com.knock.shop.item;


import org.springframework.data.domain.Page; // 이걸로 가져오기
import org.springframework.data.domain.Pageable;// 이걸로 가져오기
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
//    jpa함수 만드는 곳
Page<Item> findPageBy(Pageable page); //findPageBy 테이블에서 X개만 가져오는 함수

    List<Item> findAllByTitleContains(String title); // 검색기능

//    검색기능, 다만 full text index 기능

    @Query(value = "SELECT * FROM shop.item WHERE MATCH(title) AGAINST(?1)",  nativeQuery = true)
    Page<Item> rawQuery1(String text, Pageable page);
}

