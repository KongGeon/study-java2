package com.knock.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//보통 데이터를 보내주기 전에 비지니스로직(검사하거나 DB입출력하거나 하는 것)을 담는 클래스를 Service라고 부름
@RequiredArgsConstructor
public class ItemService {
    private  final ItemRepository itemRepository;
    public void saveItem(String title, Integer price){
        Item item = new Item(title, price);
        // 데이터베이스에 저장
        itemRepository.save(item);
    }
    // 아이템 수정 메서드
//    @Transactional
    public void updateItem(Long id, String title, Integer price) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이템이 존재하지 않습니다. ID: " + id));
        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);
        // @Transactional 어노테이션 덕분에 변경된 내용이 자동으로 DB에 반영됩니다.
    }
}
