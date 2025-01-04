package com.knock.shop.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString //내부 확인하는 방법 toString();
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(unique = true) // 중복안됨
    public String username;
    public String displayName;
    public String password;
    public Member(String password, String display_name, String username) {
        this.password = password;
        this.displayName = display_name;
        this.username = username;
    }

}
