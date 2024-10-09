package com.example.demo.controller;

import com.example.demo.service.cartDetail.CartDetailService;
import com.example.demo.service.cartDetail.response.CartDetailResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cartDetail")
public class CartDetailController {
    private final CartDetailService cartDetailService;
    @GetMapping("user/addToCart")
    public CartDetailResponse addToCart(@RequestParam Integer productId, @RequestParam int colorId , @RequestParam int sizeId , @RequestParam int quantity) {
        return    cartDetailService.addToCart(productId,colorId,sizeId,quantity);
    }
    @GetMapping("user/getListCartDetail")
    public Page<CartDetailResponse> getListCartDetail (Pageable pageable) {
        return cartDetailService.getCartDetails(pageable);
    }
}
