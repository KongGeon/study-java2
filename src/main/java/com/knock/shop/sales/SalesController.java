package com.knock.shop.sales;

import com.knock.shop.member.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor

public class SalesController {
    private final SalesRepository salesRepository;

    @PostMapping("/order")
    String postOrder(@RequestParam String title,
                     @RequestParam Integer price,
                     @RequestParam Integer count,
                     Authentication auth) {

        Sales sales = new Sales();
        sales.setItemName(title);
        sales.setPrice(price);
        sales.setCount(count);
        CustomUser user = (CustomUser) auth.getPrincipal();
        sales.setMemberId(user.id);
        salesRepository.save(sales);
        return "redirect:/list";

    }

    @GetMapping("/order/all")
    String getOrder(Authentication auth) {
        List<Sales> result = salesRepository.findAll();
        System.out.println(result.get(0));
        return "redirect:/list";
    }
}
