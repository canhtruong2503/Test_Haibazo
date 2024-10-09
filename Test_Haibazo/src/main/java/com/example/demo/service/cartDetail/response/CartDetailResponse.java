package com.example.demo.service.cartDetail.response;

import com.example.demo.service.product.response.ProductOfBillResponse;
import com.example.demo.service.product.response.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDetailResponse {
    private int id;
    private ProductOfBillResponse product;
    private ProductResponse.ColorResponse color;
    private ProductResponse.SizeResponse size;
    private int quantity;
}
