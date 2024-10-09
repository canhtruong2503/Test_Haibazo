package com.example.demo.service.bill.response;

import com.example.demo.service.product.response.ProductOfBillResponse;
import com.example.demo.service.product.response.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BillResponse {
    private int id;
    private int quantity;
    private BigDecimal price;
    private ProductOfBillResponse product;
    private ProductResponse.SizeResponse productSize;
    private ProductResponse.ColorResponse productColor;
}
