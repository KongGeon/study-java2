package com.knock.shop.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString //내부 확인하는 방법 toString();
//@Table(indexes = { //검색 빠르게 하는 방법, 대신에 글 쓰거나 할때 시간이 조금 더 오래걸릴 수 있음.
//        @Index(name = "title", columnList = "작명"),
//        @Index(name = "인덱스이름작명", columnList = "인덱스만들컬럼명2")
//})
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private Integer price;
    private String title;
    private String username;

    public Item(String title, Integer price, String username) {
        this.title = title;
        this.price = price;
        this.username = username;
    }



    public void 가격더하기(){
        this.price += 1;
    }
    public void 가격설정(Integer a){
        this.price = a;
    }
}
