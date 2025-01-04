package com.knock.shop.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
//  여기에 함수 만들어 놓으면 서비스에서 가져다가 쓸 수 있음
Optional<Member> findByUsername(String username); // Member 타입으로 가져오고 싶은데 못가져올 수 도 있으니 Optional로 제작
//Derived query methods:  findBy컬럼명(아무거나) 이거는 하나 찾는거고 findAllBy컬럼명(아무거나) 이렇게 하면 일치하는 행 전부 찾아옴, 다 찾아오게 시킬꺼면 리스트자료형에 넣는 것이 좋아보임
}
