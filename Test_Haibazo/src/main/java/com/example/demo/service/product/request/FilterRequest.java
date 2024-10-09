package com.example.demo.service.product.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FilterRequest {
    private String keyWord;
    private int colorId;
    private int sizeId;
    private int styleId;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private int categoryId;
}
