package com.knock.shop.item;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
