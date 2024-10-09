package com.example.demo.service.product.request;

import com.example.demo.model.*;
import com.example.demo.model.enums.ColorProduct;
import com.example.demo.model.enums.SizeProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal salePrice;
    private int quantity;
    private LocalDate discountTime;
    private Category category;
    private Style style;
    private List<String> images;
    private List<SizeProduct> sizes;
    private List<ColorProduct> colors;
}
