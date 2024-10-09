package com.example.demo.service.product.response;

import com.example.demo.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductOfBillResponse {
    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal salePrice;
    private LocalDate discountTime;
    private Category category;
}
